package models.calculette

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError

sealed trait Top {
  override def toString() : String = {
    this match {case Top.OUI => "O"; case Top.NON => "N" }
  }
}

object Top {
  case object OUI extends Top
  case object NON extends Top
  implicit val topRead: Reads[Top] = {
    __.read[String].filter(ValidationError("""Valeurs possibles "N" ou "O" """))(List("N", "O").contains(_)) map {
      case "O" => Top.OUI
      case "N" => Top.NON
    }
  }
}