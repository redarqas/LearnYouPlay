package controllers.ch06

import play.api.Logger
import play.api.libs.iteratee.Done
import play.api.mvc.Action
import play.api.mvc.BodyParser
import play.api.mvc.Controller
import play.api.mvc.EssentialAction
import play.api.mvc.Request
import play.api.mvc.RequestHeader
import play.api.mvc.Result
import play.api.mvc.AnyContent
import play.api.mvc.WrappedRequest

//Composition helps to decorate actions
//Action[A] is "parser" + "apply(Request[A]) : Result" to define
//Builder helper for Action[A] is apply(BodyParser[A]) (block: Request[A] => Request)

object Exercises extends Controller {
  //Logging action decorator
  def LoggingAction[A](bp: BodyParser[A])(f: Request[A] => Result): Action[A] = Action(bp) { request =>
    Logger.debug("Logging decorator")
    f(request)
  }
  //Use logging decorator 
  def index = LoggingAction(parse.text) { request =>
    Ok("I'm logged : " + request.body)
  }
  //Wrapping existing action : we will extends Action for this
  def hello = Logging {
    Action(parse.text) { request =>
      Ok("I'am wrapped action : " + request.body)
    }
  }

  //Another way to wrap existing actions
  def addUserToSession[A](action: Action[A]): Action[A] = Action(action.parser) { request =>
    action(request).withSession("user" -> "jamal")
  }
  //Add store user in session decorator  
  def user = addUserToSession {
    Action(parse.text) { request =>
      Ok("User is stored")
    }
  }

  /** ---------------------------**/
  /** Example : Authentification **/
  /** ---------------------------**/
  
  //To simplify we need a user and request for our action 
  //So we will try to define something like this :
  //action :: Request -> Result
  //richerAction :: ((User, Request) -> Result) -> Action
  //richerAction :: ((User, Request) -> Result) -> (Request -> Result)
  //richerAction f = let user = dao.user.getUser in (\request -> f(user,request))
  
  //EssentialAction : is useful to compose actions with code that requires 
  //to read some information from the request headers before parsing the request body
  def Authenticated_1(action: models.User => EssentialAction): EssentialAction = {
    def getUser(requestHeader: RequestHeader): Option[models.User] = {
      requestHeader.session.get("userMail") flatMap { userMail =>
        dao.User.findOneBy(_.email == userMail)
      }
    }
    EssentialAction { requestHeader =>
      getUser(requestHeader) map { u =>
        action(u)(requestHeader)
      } getOrElse {
        Done(Unauthorized)
      }
    }
  }
  //Action needs authenticated user
  def needAuthenticateUser = Authenticated_1 { user =>
    Action { request =>
      Ok("Priavte Action")
    }
  }
  //Autheticate a fake user : j.j@j user
  def login = Action { request =>
    Ok("You are logged, you can do : needAuthenticateUser action").withSession("userMail" -> "j@j.j")
  }

  //Without wrapping the whole action 
  def Authenticated_2(f: models.User => Request[AnyContent] => Result) = {
    Action { request =>
      val result = for {
        email <- request.session.get("userMail")
        user <- dao.User.findOneBy(_.email == email)
      } yield f(user)(request)
      result getOrElse {
        Unauthorized
      }
    }
  }

  def welcome = Authenticated_2(user => implicit request => {
    Ok("Welecome : " + user.firstName + " " + user.name)
  })
  //Using WrappedRequest
  def Authenticated_3(f: AuthenticatedRequest => Result): Action[AnyContent] = Action { request =>
    val result = for {
      email <- request.session.get("userMail")
      user <- dao.User.findOneBy(_.email == email)
    } yield f(AuthenticatedRequest(user, request))
    result getOrElse {
      Unauthorized
    }
  }

  def youCan = Authenticated_3 { ar =>
    Ok("Hello " + ar.user.firstName + " you are authenticated")
  }

}

case class Logging[A](action: Action[A]) extends Action[A] {
  def apply(request: Request[A]): Result = {
    Logger.debug("Logging existing action")
    action(request)
  }
  lazy val parser = action.parser
}

//Wrapped Request Definition
case class AuthenticatedRequest(user: models.User, rqt: Request[AnyContent]) extends WrappedRequest(rqt)

