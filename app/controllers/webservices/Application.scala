package controllers.ws

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.ws._
import scala.concurrent.Future
import play.api.libs.ws.WS.WSRequestHolder
import scala.concurrent.ExecutionContext.Implicits._
import com.ning.http.client.Realm.AuthScheme
import play.api.libs.iteratee._
import java.io.{FileOutputStream, File, BufferedOutputStream, OutputStream}

/** Uncomment the following lines as needed **/
/**
import play.api.Play.current
import play.api.libs._
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import java.util.concurrent._
import scala.concurrent.stm._
import akka.util.duration._
import play.api.cache._
import play.api.libs.json._
**/

object Application extends Controller {
  val maps = "http://maps.googleapis.com/maps/api/geocode/json"
  def call = Action.async {
    val holder :WSRequestHolder = WS.url(maps).withQueryString("address" -> "39 BD Victor Hugo", "sensor" -> "true")
    holder.get() map { response =>
     Ok(response.json)
    } recover  {
      case e => BadRequest("Oops")
    }
  }

  //with authentification
  def callAuth = Action.async {
    val holder :WSRequestHolder = WS.url(maps).withQueryString("address" -> "39 BD Victor Hugo", "sensor" -> "true")
    holder.withAuth("user", "password", AuthScheme.BASIC).get() map { response =>
      Ok(response.json)
    } recover  {
      case e => BadRequest("Oops")
    }
  }

  //with redirect in case of 301/
  //additional headers
  def options = Action.async {
    val holder :WSRequestHolder = WS.url(maps).withQueryString("address" -> "39 BD Victor Hugo", "sensor" -> "true")
    holder.
      withFollowRedirects(true).
      withHeaders("headerKey" -> "headerValue").
      withRequestTimeout(100).
      get() map { response =>
      Ok(response.json)
    } recover  {
      case e => BadRequest("Oops")
    }
  }

  //Process large response
  def fromStream(stream: OutputStream) : Iteratee[Array[Byte], Unit] = Cont {
    case e@Input.EOF =>
      stream.close()
      Done((), e)
    case Input.Empty =>
      fromStream(stream)
    case e@Input.El(data) =>
      stream.write(data)
      fromStream(stream)
  }

  val out = new BufferedOutputStream(new FileOutputStream(new File("/tmp/result.json")))
  val largeFuture = WS.url(maps).withQueryString("address" -> "39 BD Victor Hugo", "sensor" -> "true").get {
    _ => fromStream(out)
  }.map(_.run)

  //process large ws response
  def large = Action.async {
    largeFuture map { _ =>
      Ok("Result loaded")
    } recover {
      case e =>BadRequest("Oops : " + e.getMessage)
    }
  }



}