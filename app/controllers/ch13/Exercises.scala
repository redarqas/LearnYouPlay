package controllers.ch13

import play.api.libs.iteratee.Enumerator
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.ResponseHeader
import play.api.mvc.SimpleResult
import play.api.mvc.WebSocket
import play.api.libs.iteratee.Iteratee
import play.api.data._
import play.api.data.Forms._

//Form = a Set of associations between a String key and mapping (field)
//A mapping is key and eventually constraints on this field
//Once we defined the Form, 
//We can construct it (transform it into Scala value)
//and deconstruct it (tarnsform a Scala value into a Form)

object Exercises extends Controller {
  
  val logingForm = Form(tuple(
        "email" -> text.verifying(),
        "password" -> text
      ))
}