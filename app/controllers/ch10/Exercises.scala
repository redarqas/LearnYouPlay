package controllers.ch10

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json._
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Enumeratee
import play.api.libs.Comet

//Comet socket is just a chunked text/html response containing only <script> elements
//At each chunk we write a <script> tag that is immediately executed by the web browser
object Exercises extends Controller {

  def comet = Action {
    val events = Enumerator(
      """<script>console.log('toto 1 from comet')</script>""",
      """<script>console.log('toto 2 from comet')</script>""",
      """<script>console.log('toto 3 from comet')</script>""")
    Ok.stream(events andThen Enumerator.eof).as(HTML)
  }

  //Better comet : transform a stream of string into a comet (stream of script html tags)
  def betterComet = Action {
    //Not really a dynamic events generator
    val events = Enumerator("toto message", "tata message", "titi message")
    //Create an  
    val toCometMessages: Enumeratee[String, play.api.templates.Html] = Enumeratee.map[String] { s =>
      play.api.templates.Html("""<script>console.log("""" + s + """")</script> """)
    }
    //through uses the Enumeratee[String, Html] to create 
    //an Enumerator[html] from a Enumerator[String]
    Ok.stream(events through toCometMessages andThen Enumerator.eof)
  }

  //Using comet helper
  def helperComet = Action {
    val events = Enumerator("hello", "bonjour", "qué tal")
    Ok.stream(events through Comet(callback = "console.log") andThen Enumerator.eof)
  }

  //Iframe technique
  def parentComet = Action { request =>
    Ok(views.html.index("comet parent"))
  }
  def iframeComet = Action {
    val events = Enumerator("hello", "bonjour", "qué tal")
    Ok.stream(events through Comet(callback = "parent.cometMessage") andThen Enumerator.eof)
  }

}