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
  val productForm = Form {
    mapping(
      "id" -> ignored(123l),
      "name" -> text,
      "price" -> bigDecimal)(models.Product.apply _)(models.Product.unapply _)
  }
  

}