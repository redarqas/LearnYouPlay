package controllers.forms

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import models.forms._
import play.api.data.validation.Constraints._

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
  //Define a form
  val userForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number.verifying(min(0), max(10)))(UserData.apply)(UserData.unapply))

  //read from map
  //val map = Map("name" -> "Jamal", "age" -> "23")
  //val user = userForm.bind(map).get
  //Custom validation
  def validateUser(name: String, age: Int): Option[UserData] = name match {
    case "bob" if age >= 1 => Some(UserData(name, age))
    case _ => None
  }
  val userForm2 = Form(
    mapping(
      "name" -> text,
      "age" -> number)(UserData.apply)(UserData.unapply) verifying ("Failed form constraints!", userdata =>
        validateUser(userdata.name, userdata.age).isDefined))

  //validate a form in Action
  def show() = Action {
    Ok(views.html.forms.user(userForm))
  }

  def createUser = Action { implicit request =>
    userForm.bindFromRequest().fold(
      errors => BadRequest(views.html.forms.user(errors)),
      userDate => Ok("User Data Ok"))

  }

  def editUser(id: Long) = Action {
    val user = UserData("Claire", 32)
    val filled = userForm.fill(user)
    Ok(views.html.forms.user(filled))
  }

}