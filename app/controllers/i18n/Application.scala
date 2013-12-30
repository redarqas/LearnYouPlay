package controllers.i18n

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.Messages
import java.io.File

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

  def usage = Action { request =>
    val acceptedLangs = request.acceptLanguages.map(_.code).mkString(", ")
    val current = new File(".")
    val children = current.listFiles.size
    val message = Messages("custom.massage", current.getName, children)
    Ok(acceptedLangs + " : " + message)
  }

}