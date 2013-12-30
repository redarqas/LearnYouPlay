package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
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
  def list() = Action { implicit request =>
    val AcceptsMp3 = Accepting("audio/mp3")
    render {
      case Accepts.Html => Ok("render html template")
      case Accepts.Json => Ok("render Json")
      case AcceptsMp3 => Ok("Serve MP3")
    }
  }

}