package controllers.parsers

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
  //parse text
  def save() = Action { request =>
    val body: AnyContent = request.body
    val textBody: Option[String] = body.asText
    textBody.map { text =>
      Ok("Got " + text)
    } getOrElse {
      BadRequest("Oops, Expect text/plain request body")
    }
  }
  //parse text
  def read() = Action(parse.text) { request =>
    Ok("Text : " ++ request.body)
  }
  //do not check content-type
  def tolerant() = Action(parse.tolerantText) { request =>
    Ok("Text tolerant : " ++ request.body)
  }
  //parse file example
  def tofile = Action(parse.file(to = new java.io.File("/tmp/upload.txt"))) { request =>
    Ok("Saved to : " ++ request.body.getName())
  }
  //Combine parsers
  val storeInUserFile = parse.using { request =>
    request.session.get("user").map { user =>
      parse.file(to = new java.io.File("/tmp/" ++ user ++ ".txt"))
    } getOrElse {
      sys.error("no permissions")
    }
  }
  def saveUserFile = Action(storeInUserFile) { request =>
    Ok("Saved to : " ++ request.body.getName())
  }

  def welcome(name: String) = Action { request =>
    Ok("welcome " ++ name).withSession(request.session + ("user" -> name))
  }
  //parser max lengh
  def textMax = Action(parse.maxLength(1024 * 10, storeInUserFile)) { request => 
    Ok("Saveed with limit : " ++ request.body.toString)
  } 
}