package controllers.ch01

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._

//The controller Application is a singleton that generates Actions
object Exercises extends Controller {

  /** -------------------------------------- **/
  /** Define Actions                         **/
  /** -------------------------------------- **/
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  // Define an action with val keyword
  val echoVal = Action { request =>
    Ok("request is [" + request + "]")
  }

  // Define an expression using a simple expression block
  def echoBlock = Action {
    Ok("Simple block")
  }

  //Action with reference to request 
  def echoRequest = Action { request =>
    Ok("I have a referce to the HTTP request wrapper " + request)
  }

  //Action with request decalared impilicit for nested call
  def echoImplicitRequest = Action { implicit request =>
    Ok("I have a reference to the request and it's impiticit for other calls : " + request)
  }

  //Action with a specified body parser  (a raw one)
  def echoWithBodyParser = Action(parse.raw) { request =>
    Ok("Action that parses the body : " + request.body)
  }

  //What about parameters : An example with one parameter 
  def echoProvidedName(name: String) = Action { request =>
    Ok("Hello " + name)
  }

  /** -------------------------------------- **/
  /** Define simple results                  **/
  /** -------------------------------------- **/

  //OK is a syntaxic sugar to this simple result
  def simpleOkResult = Action {
    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Hello simple result"))
  }

  def ok = Action {
    Ok("Tout va bien")
  }
  def other = Action {
    Status(488)("Aïe !")
  }

  //Redirect are simple results too
  def redirectMe() = Action {
    Redirect("/", MOVED_PERMANENTLY)
  }

  def redirectToAction(name: String) = Action {
    Redirect(routes.Exercises.echoProvidedName(name))
  }

  //Empty Action to signal a TODO 
  def myTODO = TODO
  
  /** -------------------------------------- **/
  /** Http routing (see conf/routes files)   **/
  /** -------------------------------------- **/
  
  //Span several /
  def spanIt(theSpan: String) = Action { 
    Ok("That what i span atually :  "+ theSpan)
  }
  
  //Regular expression : limit to 0 or 1
  def limitZeroOne(id: Long) = Action {
    Ok("Your in the limit of [0-1] : "+ id)
  }

  //Parameter with a default value 
  def withDefault(id : Long) = Action { 
    Ok("Default value is : " + id)
  }
  
  //Optional parameter 
  def optionalParam(version: Option[String]) = Action {
     Ok("Version is optional : " + version)
  }
  //Reverse routing usage for redirect
  def reverseEchoProvidedName(name: String) = Action {
    Redirect(routes.Exercises.echoProvidedName(name))
  }
  
}