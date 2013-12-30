package controllers.stream

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
 * import play.api.libs.iteratee._
 * import play.api.libs.concurrent._
 * import java.util.concurrent._
 * import scala.concurrent.stm._
 * import akka.util.duration._
 * import play.api.cache._
 * import play.api.libs.json._
 */

object Application extends Controller {
  //Stream : we have to give CONTENT-LENGTH header option
  def index = Action {
    val file = new java.io.File("/tmp/Haskell.pdf")
    val fileContent = Enumerator.fromFile(file)
    SimpleResult(header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = fileContent)
  }
  //play helper for file
  def serve = Action {
    Ok.sendFile(new java.io.File("/tmp/Haskell.pdf"))
  }
  //serve file with option
  def serveFile = Action {
    Ok.sendFile(content = new java.io.File("/tmp/Haskell.pdf"),
      inline = true,
      fileName = _ => "learnYouHaskell.pdf")
  }
  //Chuncked responses : use Transfer-Encoding

  def chunked = Action {
    val chunkedFile: Enumerator[Array[Byte]] = Enumerator.fromFile(new java.io.File("/tmp/haskell.pdf"))
    Ok.chunked(chunkedFile)
  }
  def enumit = Action {
    Ok.chunked(
      Enumerator("Jamal", "Claire", "Ilyas").andThen(Enumerator.eof))
  }


}