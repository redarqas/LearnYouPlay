package controllers.ch07

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._

//Content negotiation : same resource, multiple outputs (XML, Json, ...)
//To serve the right output : negociation is based on Accept* header parameter
//Accept-Language and Accept for Media Ranges 
object Exercises extends Controller {
  //Render according a range of medialist
  def list = Action { implicit request =>
    val user = dao.User.findAll()
    render {
      case Accepts.Html() => Ok("I will render Html for you")
      case Accepts.Json() => Ok("I will render Json for you")
    }
  }
  //Create a custom extractor for a given MIME Type
  val AcceptsSylk = Accepting("text/sylk")
  def sylk = Action { implicit request => 
    render {
      case AcceptsSylk => Ok("I will serve you sylk content")
    }
  }
}