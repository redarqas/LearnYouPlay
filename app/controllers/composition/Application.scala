package controllers.composition

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.concurrent._
import scala.concurrent._
/** Uncomment the following lines as needed **/
/**
 * import play.api.Play.current
 * import play.api.libs._
 * import play.api.libs.iteratee._
 *
 *
 * import scala.concurrent.stm._
 * import akka.util.duration._
 * import play.api.cache._
 * import play.api.libs.json._
 */

object Application extends Controller {

  //Action builder
  object LoggingAction extends ActionBuilder[Request] {
    def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[SimpleResult]) = {
      Logger.info("Calling action")
      block(request)
    }
  }

  object SecuredAction extends ActionBuilder[Request] {
    def userinfo(request: RequestHeader): Option[String] = request.session.get("email")
    def onUnauthorized(request: RequestHeader): SimpleResult = Results.Redirect(routes.Application.index)
    def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[SimpleResult]) = {
      Security.AuthenticatedBuilder
      Logger.info("Calling action")
      block(request)
    }
  }

  def index = LoggingAction(parse.json) { implicit request =>
    Ok("Hello World with log")
  }

  def submit = LoggingAction(parse.text) { request =>
    Ok("Got a body " + request.body.length + " bytes long")
  }

  //Action composition
  class AuthenticatedRequest[A](userName: String, request: Request[A]) extends WrappedRequest[A](request)

  object Authenticated extends ActionBuilder[AuthenticatedRequest] {
    def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[SimpleResult]) = {
      request.session.get("username").map { user =>
        block(new AuthenticatedRequest(user, request))
      } getOrElse {
        Future.successful(Forbidden)
      }
    }
  }

  
  def login(user: String) = Action { request =>
    Ok("Welcome ").withSession(request.session + ("username" -> user))
  }

  def currentUser() = Authenticated(parse.tolerantText) { request =>
    Ok("logged")
  }

  def logging[A](action: Action[A]) = Action.async(action.parser) { request =>
    Logger.info("Calling action")
    action(request)
  }

  def log() = logging {
    Action {
      Ok("simple composition")
    }
  }

}