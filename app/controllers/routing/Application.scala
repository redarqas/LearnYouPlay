package controllers.routing

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

object Application extends Controller {
  //parameter
  def show(name: String) = Action {
    Ok("Got : " ++ name)
  }
  //dynamic part
  def files(name: String) = Action {
    Ok("requested file : "++name)
  }
  //regex
  def someUsers(id: Long) = Action {
    Ok("requested numeric id  : " ++ id.toString)
  }
  //fixed 
  def page(name: String) = Action {
    Ok("fixed page : "++ name)
  }
  //default
  def customer(id: Long) = Action {
    Ok("Default value " ++ id.toString)
  }
  //Optional
  def list(version: Option[String]) = Action {
    version match {
        case None => BadRequest("No version")
        case Some(v) => Ok(v)
    }
  }
  //reverse routing
  def reverse() = Action {
    Redirect(routes.Application.show("Jamal"))
  }

}