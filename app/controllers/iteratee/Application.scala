package controllers.iteratee
import play.api.mvc._
import play.api.libs.iteratee._
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.concurrent.Future._
import play.api.libs.ws._
import Examples._
import play.api.libs.concurrent.Promise
import java.util.Date
import scala.concurrent.duration.Duration
import scala.concurrent.duration.Duration._
import scala.concurrent.duration._
import scala.language.postfixOps

object Application extends Controller {
  //Define done iteratee
  val doneIter: Iteratee[_, Int] = createDoneIter(1, Input.Empty)
  //get the result from iteratee
  def foldDone(step: Step[_, Int]): Future[Option[Int]] = step match {
    case Step.Done(e, _) => successful(Some(e))
    case _ => successful(None)
  }
  def done = Action.async {
    doneIter.fold(foldDone) map { o =>
      Ok("Result" + o)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }
  //Define first iteratee
  val firstIter: Iteratee[_, _] = creatFirstInput()
  //Extract iter result
  def foldFirst(step: Step[_, _]): Future[_] = step match {
    case Step.Done(x, _) => successful(x)
    case Step.Error(msg, _) => failed(new Exception(msg))
    //Push EOF if Iteratee is not tereminated
    case Step.Cont(k) => k(Input.EOF).fold({
      case Step.Done(x, _) => successful(x)
      case Step.Error(msg, _) => failed(new Exception(msg))
      case Step.Cont(_) => failed(new Exception("Divergent Iteratee"))
    })
  }
  def firstOne = Action.async {
    firstIter.fold(foldFirst) map { r =>
      Ok("Result" + r)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }
  /** Fold an iteratee **/
  val wsCall = WS.url("http://maps.googleapis.com/maps/api/geocode/json")
    .withQueryString("address" -> "Victor Hugo", "sensor" -> "true")
  //Iteratee used to process response
  def countIter: Iteratee[Array[Byte], Int] = Iteratee.fold(0)((acc, e) => acc + 1)

  def length = Action.async {
    val result = wsCall get {
      _ => countIter
    } flatMap (_.run)

    result map { length =>
      Ok("Number of received chunks " + length)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }

  val enum: Enumerator[String] = Enumerator("Jamal", "Claire", "Ilyes")
  /** Enemurator **/
  def firstWithFeed = Action.async {
    val r = enum(creatFirstInput()).flatMap(iter => iter.run)
    r map { f =>
      Ok("Result : " + f)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }

  def allFeedsInOne = Action.async {
    val result = Iteratee.flatten(enum |>> Iteratee.consume[String]()).run
    result map { f =>
      Ok("Result : " + f)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }

  def fromFile = Action.async {
    val r = 10 seconds
    val enumFile: Enumerator[Array[Byte]] = Enumerator.fromFile(new java.io.File("/private/var/log/wifi.log"))
    val iter = enumFile |>> Iteratee.fold(0)((acc, e) => acc + e.size)
    val iterResult = Iteratee.flatten(iter).run
    iterResult map { size =>
      Ok("Result size : " + size)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }
  //Enumerator from callbacks
  def willneverfinish = Action.async {
    val enum = Enumerator.repeat(3)
    val iter = enum |>> Iteratee.fold(0)((acc, e) => acc + e)
    val iterResult = Iteratee.flatten(iter).run
    //It's an infinite stream : the future will never be redeemed
    iterResult map { size =>
      Ok("Result size : " + size)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }
  //Enumerator as play stream
  def stream = Action.async { implicit request =>
    val enum1 = Enumerator.repeat("un")
    val enum2 = Enumerator.repeat("deux")
    val enum = Enumerator.interleave(enum1, enum2)
    successful(Ok.chunked(enum))
  }

  //Generate Enumeartor
  val gen = Enumerator.generateM {
    Promise.timeout(Some(new Date()), 1)
  }
  def generate = Action.async {
    successful(Ok.chunked(gen.map(_.toString())))
  }
  //Brodcast 
  val (multiIter, channel) = Concurrent.broadcast[String]
  //attach iteratees to multiIter
  (1 to 3) map { i =>
    multiIter |>> Iteratee.foreach(e => println("Iter n° " + i + " Elem " + e))
  }
  def broadcast = Action.async {
    (1 to 300000) map { i =>
      channel.push("=====" + i + "=====\n")
    }
    successful(Ok.chunked(multiIter))
  }
  //Display fold intermediate results of an iteratee
  def willfinish = Action.async {
    val stream = Enumerator.enumerate(Stream.from(1))
    //Define a scanLeft using Enumerator directly
    val enum = stream.scanL(0)((acc, e) => acc + e)
    successful(Ok.chunked(enum.map(_ + "\n")))
  }
  /** Enumeratee : better solution for adapting inputs **/
  def map = Action.async {
    //Map can be done directly with Enumerator API
    val stream: Enumerator[Char] = Enumerator.enumerate(('a' to 'z'))
    type Line = String
    val mapToWritabe: Enumeratee[Char, Line] = Enumeratee.map(_ + "\n")
    successful(Ok.chunked(stream &> mapToWritabe))
  }

  def scan = Action.async {
    //If you want to push from the same enumerator, use brodacast
    val sum: Iteratee[Int,Int] = Iteratee.fold[Int,Int](0){ (s,e) => println("Char "+ (s + e));s + e }
    val scan: Enumeratee[Char, Int] = Enumeratee.scanLeft[Char](0)((acc, e) => acc + e.toInt)
    val mapToWritabe: Enumeratee[Int, String] = Enumeratee.map(_ + "\n")
    val enumAdapter:Enumeratee[Char, String] = scan ><> mapToWritabe
    val toInt: Enumeratee[Char,Int] = Enumeratee.map[Char]{ c => c.toInt } 
    val adaptedIteratee: Future[Iteratee[Char,Int]] = Enumerator.enumerate(('a' to 'z')) |>> toInt.transform(sum)
    successful(Ok.chunked(Enumerator.enumerate(('a' to 'z')) &> enumAdapter))
  }
}