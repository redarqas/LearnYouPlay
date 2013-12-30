package controllers.websocket

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits._
/** Uncomment the following lines as needed **/
/**
 * import play.api.Play.current
 * import play.api.libs._
 * import play.api.libs.concurrent._
 * import java.util.concurrent._
 * import scala.concurrent.stm._
 * import akka.util.duration._
 * import play.api.cache._
 * import play.api.libs.json._
 */
/**
 * WebSocket is a web technology that provides bi-directional, full-duplex communication channels
 */
object Application extends Controller {
  def index = WebSocket.using[String] { request =>
    val in = Iteratee.foreach[String](println).map { _ => println("Disconnected") }
    val out = Enumerator("Hello")
    (in, out)
  }

  //Multiple clients
  def broadcast = WebSocket.using[String] { request =>
    val (out, channel) = Concurrent.broadcast[String]
    val in = Iteratee.foreach[String] { msg =>
      println(msg)
      channel push ("Response : " ++ msg)
    }
    (in, out)
  }

}