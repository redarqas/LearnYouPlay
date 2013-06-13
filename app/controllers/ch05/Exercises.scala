package controllers.ch05

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._
import play.api.mvc.BodyParsers.parse._

//BodyParser is used to parse body from "POST"/"PUT" request 
//BodyParser transform the request body into a Scala value
//BodyParser is ~ an Iteratee[Array[Byte],Either[Result,A]]
//BodyParser has access to HTTP and can make some pre-conditions checks
//Summerize : Action[A] -> BodyParser[A] -> Request[A] -> body == A
//A is your Choosen Scala Value

object Exercises extends Controller {

  //Defualt BodyParser : AnyContent
  def save = Action { request =>
    val body = request.body
    val textBody = body.asText
    textBody map { text =>
      Ok("Your text is : " + text)
    } getOrElse {
      BadRequest("Your Content-Type is not text/plain")
    }
  }

  //Play Body Parser are available here : play.api.mvc.BodyParsers.parse
  //parser.text is equivalent to AnyContent.asText
  //With this parser we have already : 400 BAD_REQUEST 
  //If something goes wrong
  def saveText = Action(parse.text) { request =>
    val textToSave:String = request.body
    Ok("Text to save is" + textToSave)
  }
  
  //Upload file parser : Store  
  def saveUpload = Action(parse.file(to = new java.io.File("/tmp/upload.txt"))) { request =>
    val file : java.io.File = request.body
    Ok("The content of file " + file.getAbsolutePath())
  }
  
  //Different files for different users 
  val storInUserFile = parse.using { request =>
    request.session.get("userName") map { name =>
      file(to = new java.io.File("/tmp/"+name+".upload"))
    } getOrElse {
      error(Unauthorized("sdsds"))
    }
  }
  //Use the user specific bodyparser defined above
  def saveUploads = Action(storInUserFile) { request =>
    Ok("Stored in different files")
  }
  //Just utility for uploads : login and then saveUploads
  def login = Action { request =>
    Ok("Your are logged").withSession("userName" -> "haha")
  }
  
  //Max content length : for memory usage
  //Accept only 10KB
  def limitedText = Action(parse.text(maxLength = 1024 * 10)) { request =>
    Ok("The input text size is limited to 10KB")
  }
  
  def saveLimitUploads = Action(maxLength(parser = storInUserFile, maxLength = 1024 * 10)) { request =>
    Ok("Stored in different files")
  }
  
  
}