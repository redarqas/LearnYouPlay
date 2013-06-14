package controllers.ch08

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json._
import models.User

//Asynchronous results : The web client will be blocked while waiting for the response,
//but nothing will be blocked on the server, and server resources can be used to serve 
//other clients.

object Exercises extends Controller {
  //Async helps to create an AsyncResult From a Future[Result]
  def accessDB = Action { implicit request =>
    //Access could be a long computation
    val users: Future[Seq[models.User]] = Future { dao.User.findAll() }
    Async {
      users map { u => Ok(Json.toJson(u))
      } recover {
        case _ => BadRequest("OOPs")
      }
    }
  }

  //Timeout : the client will be released 
  //after the duration of timeout
  def accessDBTimeout = Action { request =>
    //Add timeout definition
    val tiemoutFuture = play.api.libs.concurrent.Promise.timeout("Oops", 1)
    val users: Future[Seq[models.User]] = Future { dao.User.findAll() }
    Async {
      Future.firstCompletedOf(Seq(tiemoutFuture, users)) map {
        case u: Seq[User] => Ok(Json.toJson(u))
        case s: String => BadRequest(s)
      }
    }
  }

}