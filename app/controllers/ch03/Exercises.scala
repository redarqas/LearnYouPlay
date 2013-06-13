package controllers.ch03

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._

object Exercises extends Controller {

  //Change the default charset 'utf-8' for whole controler : by importing an implicit one
  implicit val myCustomCharset = Codec.javaSupported("iso-8859-1")
  
  def charsetIso_8859_1 = Action {
    Ok(<h1>Hello World!</h1>).as(HTML)
  }
  //Manipulate headers
  def hello = Action {
    Ok("Hello World").withHeaders(CACHE_CONTROL -> "max-age=3600", ETAG -> "xx")
  }

  //Setting and discarding Cookies 
  def helloAddCookie = Action {
    Ok("Hello World, I'am setting a cookie").withCookies(Cookie("RealName", "Jamal CHAQOURI"))

  }
  //Discard cookie
  def helloDiscardCookie = Action {
    Ok("Hello World, I'am setting a cookie").discardingCookies(DiscardingCookie("RealName"))
  }
  

}