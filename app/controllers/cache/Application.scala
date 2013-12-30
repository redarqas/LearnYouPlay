package controllers.cache

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.cache.{Cached, Cache}
import play.api.Play.current

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
   def login(name:String) = Action {
     Cache.set("connectedUser", name)
     Redirect("/cache/welcome")
   }

   def welcome = Action {
     Cache.getAs[String]("connectedUser") map {user =>
       Ok("Welcome " + user)
     } getOrElse {
       Unauthorized("Oops")
     }

   }
   //Cache response
   def home = Cached("homepage") {
     Action {
       Ok("Home page")
     }
   }
  

}