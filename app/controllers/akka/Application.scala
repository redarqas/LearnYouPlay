package controllers.akka

import play.api._
import play.api.mvc._
import play.api.libs.concurrent._
import akka.actor._
import play.api.Play.current
import akka.pattern.ask
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.duration._
import akka.util.Timeout
import scala.concurrent.Future

object Application extends Controller {
  val counter = Akka.system.actorOf(Props[Counter], "counter")
  implicit val timeout = Timeout(5 seconds)
  def increment = Action.async {
    counter ! "incr" 
    (counter ? "get").mapTo[Int].map { i =>
      Ok("Count : " + i)
    } recover {
      case e => BadRequest(e.getMessage())
    }
  }
}