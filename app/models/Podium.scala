package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data.validation.ValidationError

sealed trait Podium

object Podium {

  case object PODIUM_OR extends Podium
  case object PODIUM_AR extends Podium
  case object PODIUM_BR extends Podium

  implicit val podiumRead: Reads[Podium] = {
    //Read with validation
    __.read[String].filter(ValidationError("Valeurs possible [OR, AR, BR]"))(List("OR", "AR", "BR").contains(_)) map {
      case "OR" => PODIUM_OR
      case "AR" => PODIUM_AR
      case "BR" => PODIUM_BR
    }
  }

  implicit val podiumWrite = new Writes[Podium] {
    override def writes(p: Podium) = p match {
      case PODIUM_OR => JsString("OR")
      case PODIUM_AR => JsString("AR")
      case PODIUM_BR => JsString("BR")
    }
  }
}