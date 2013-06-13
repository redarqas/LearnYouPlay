package models

import play.api.libs.json._

case class User(id: Long, name:String, firstName: String, email:String)

object User {
  implicit val userRead = Json.reads[User]
  implicit val userWrite = Json.writes[User]
}