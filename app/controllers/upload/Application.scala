package controllers.upload

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

  //page for upload
  def show() = Action {
    Ok(views.html.upload.show())
  }
  //upload : multipart/form-data
  def  upload() = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { picture =>
      import java.io.File
      val filename = picture.filename
      picture.ref.moveTo(to = new File(s"/tmp/$filename"))
      Ok("File uploaded")
    } getOrElse {
      Redirect(routes.Application.show).flashing("error" -> "Missing file")
    }
  }
  //Direct upload : Ajax call without multipart/form-data
  def directUpload() = Action(parse.temporaryFile) { request =>
     request.body.moveTo(new java.io.File("/tmp/picture"))
     Ok("File uploaded")
  }

}