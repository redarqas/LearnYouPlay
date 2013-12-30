package controllers.async

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.data._
import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps


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
  * */

object Application extends Controller {

  val futureInt = Future {
    1
  }

  def index = Action.async {

    val timeoutFuture = play.api.libs.concurrent.Promise.timeout("Oops", 1 seconds)
    Future.firstCompletedOf(Seq(futureInt, timeoutFuture)).map {
      case i: Int => Ok("Result : " ++ i.toString)
      case s: String => BadRequest(s)
    } recover {
      case e => BadRequest(e.getMessage)
    }
  }

}