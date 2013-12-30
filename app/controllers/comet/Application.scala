package controllers.comet

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.iteratee._
import play.api.libs.Comet
import play.api.libs.concurrent.Execution.Implicits._
import play.api.templates.Html
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
 * A Comet socket is just a chunked text/html response containing only script elements.
 * At each chunk we write a script tag that is immediately executed by the web browser
 * one-way communication from the server to the client.
 * To push events to the server, the web browser has to send Ajax requests.
 */
object Application extends Controller {
  def events = Action {
    val events = Enumerator(
      """<script>console.log('kiki')</script>""",
      """<script>console.log('foo')</script>""",
      """<script>console.log('bar')</script>""")
    Ok.chunked(content = events >>> Enumerator.eof).as(HTML)
  }

  val toCometMessage = Enumeratee.map { data: String =>
    Html("""<script>console.log('"""++data++"""')</script>""")
  }
  //With enumeratee
  def cometEvents = Action {
    val enumEvents = (Enumerator("Jamal", "Claire", "Ilyas") &> toCometMessage) >>>  Enumerator.eof
    Ok.chunked(enumEvents)
  }
  //Comet helper
  def cometHelp = Action {
    val events = Enumerator("Jamal", "Claire", "Ilyas")
    Ok.chunked(events >>> Enumerator.eof &> Comet(callback = "console.log"))
  }
  

}