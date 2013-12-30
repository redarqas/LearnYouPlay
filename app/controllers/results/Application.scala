package controllers.results

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
/** Uncomment the following lines as needed **/
/**
import play.api.Play.current
import play.api.libs._
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import java.util.concurrent._
import scala.concurrent.stm._
import akka.util.duration._
import play.api.cache._
import play.api.libs.json._
**/

object Application extends Controller {

  //text result
  def text() = Action {
    Ok("content is text")
  }
  //xml
  def xml() = Action {
    Ok(<message>Our message</message>)
  }
  //xml as text
  def xmlAsText = Action {
    Ok(<message>Our message</message>).as(TEXT)
  }
  //with headers
  def header = Action {
    Ok("Add some header option").withHeaders(CACHE_CONTROL -> "max-age=3600",
    ETAG -> "eg")
  }

}