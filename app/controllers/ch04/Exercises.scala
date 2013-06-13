package controllers.ch04

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._

//Session and Flash are not stored by the server
//They are added to each request
//Stored data size is very limited (<4kB)
//Session is not a cache mecanism

object Exercises extends Controller {

  /** --------------------------------- **/
  /**   Session                         **/
  /** --------------------------------- **/
  //The session cookie is signed 

  //Store data in the session 
  def login = Action {
    Ok("Welcome ! ").withSession("userMail" -> "stored@in.session")
  }
  //Retrieve data from session 
  def welecome = Action { request =>
    request.session.get("userMail") map { um =>
      Ok("Your mail is : " + um)
    } getOrElse {
      Unauthorized("Oops, you have no mail")
    }
  }

  def logout = Action { request =>
    Ok("Logout").withSession(
      request.session - "userMail")
  }
  //Discard all session
  def discardFullSession = Action { request =>
    Ok("dsicard All session").withNewSession
  }

  /** --------------------------------- **/
  /** Flash Scope                       **/
  /** --------------------------------- **/
  //Flash is like session : 
  //data is kept only for one request
  //the flash cookie is not signed

  def save = Action { implicit request =>
    Redirect("/ch04/home").flashing(
      "success" -> "The item has been created")
  }

  def home = Action { implicit request =>
    println("flash is available here " + flash.get("success"))
    Redirect("/ch04/lostFlash")
  }
  
  def lostFlash = Action { implicit request =>
   Ok {
      flash.get("success").getOrElse("Welcome!")
    }
  }

}