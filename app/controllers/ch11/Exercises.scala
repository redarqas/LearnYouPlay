package controllers.ch11

import play.api.libs.iteratee.Enumerator
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.ResponseHeader
import play.api.mvc.SimpleResult
import play.api.mvc.WebSocket
import play.api.libs.iteratee.Iteratee

//WebSockets provides bi-directional (full-duplex communication channels)
//communication between the browser and the server
//The Websocket has access to the header
//but has no access to request not to the http response
object Exercises extends Controller {
  //Utility method
  def fakeStream[A](i: Int, n: A): List[A] = i match {
    case 0 => Nil
    case i => n :: fakeStream((i - 1), n)
  }
  //Simple example
  def index = WebSocket.using[String] { request =>
    //read input
    val in: Iteratee[String, _] = Iteratee.foreach[String](println).mapDone { _ =>
      println("Disconnected")
    }
    //Send a list of messages to 
    val out: Enumerator[String] = Enumerator(fakeStream[String](1000, "OH\n"): _*)
    (in, out)
  }
  //Example : close the socket
  def close = WebSocket.using[String] { request =>
    val in = Iteratee.consume[String]()
    val out = Enumerator("This is the end") andThen Enumerator.eof
    (in, out)
  }
}