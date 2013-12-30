package controllers.sessions

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
  //read session
  def read() = Action { implicit request =>
    session.get("connected").map { user =>
      Ok("user : " + user)
    } getOrElse {
      Unauthorized("Oops, you're not connected")
    }
  }
  //set session
  def welcome() = Action { implicit request =>
    Ok("welcome").withSession(session + ("connected" -> "a@a.c"))
  }
  //remove
  def bye() = Action { implicit request =>
    Ok("bye bye").withSession(session - "connected")
  }
  //discard session
  def goodBye() = Action { implicit request =>
    Ok("Good bye").withNewSession
  }
  //read flash
  def getFlash() = Action { implicit request =>
    Ok {
      flash.get("success").getOrElse("Welcome !")
    }
  }
  def save() = Action { implicit request =>
    Redirect("/sessions/getFlash").flashing("success" -> "The item has been created")
  }

}