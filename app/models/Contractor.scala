package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError

case class Contractor(name: String, email: String, garantie: models.Garantie, complement: Option[String])

object Contractor {
    implicit val readContractor: Reads[Contractor] = (
      (__ \ "name").read[String] (email) and
      (__ \ "email").read[String] and
      (__ \ "garantie").read[models.Garantie] and
      (__ \ "complement").readNullable[String])(Contractor.apply _)

  
}