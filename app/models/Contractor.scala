package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import models.Podium._

case class Contractor(complement: Option[String], name: String, email: String, podium: models.Podium)

object Contractor {
  //Validation rule : required if podium is OR
  val readComplement = ((__ \ "podium").readNullable[String] and
    (__ \ "complement").readNullable[String]).tupled.filter(ValidationError("complement obligatoire si podium est à OR"))(r => r match {
      case (Some("OR"), None) => println("false : "+ r);true
      case _ => println("true : "+ r);false
    }) map (_._2)
  
  implicit val readContractor: Reads[Contractor] = (readComplement and
    (__ \ "name").read[String](email) and
    (__ \ "email").read[String] and
    (__ \ "podium").read[models.Podium])(Contractor.apply(_,_,_,_))
  //Using macr for json writing
  implicit val writeComplement = Json.writes[Contractor]

}