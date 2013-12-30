package controllers.templating

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import models.templating._

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
   def show = Action {
     val orders : Seq[Order] = List(Order("ipad", 1))
     val products : Seq[Product] = List(Product("ipad", 399.9))
     val customer : Customer = Customer("Jamal", "Chaqouri", 32)
     val escaped = "<p> cool </p1>"
     Ok(views.html.templating.show(customer, orders, products, escaped))
   }
}