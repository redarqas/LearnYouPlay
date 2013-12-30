package controllers.actions

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.iteratee.Enumerator

/** Uncomment the following lines as needed **/
/**
 * import play.api.Play.current
 * import play.api.libs._
 * import play.api.libs.iteratee._
 * import play.api.libs.concurrent._
 * import java.util.concurrent._
 * import scala.concurrent.stm._
 * import akka.util.duration._
 * import play.api.cache._
 * import play.api.libs.json._
 */

object Application extends Controller {

  def index() = Action { request =>
    Ok("Hello world")
  }

  def hello(name: String) = Action {
    Ok("Hello " ++ name)
  }

  def simple() = Action {
    SimpleResult(header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Hello world".getBytes))
  }

  def redirect() = Action {
    Redirect("/actions/hello")
  }

  def anyStatus = Action {
    Status(488)("Strange response type")
  }

  def todo = TODO
}