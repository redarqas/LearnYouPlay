package models.calculette

import play.api.libs.json._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError

sealed trait Regime {
  override def toString() : String = {
    this match {case Regime.REGIME1 => "1"; case Regime.REGIME2 => "2" }
  }
}

object Regime {
  case object REGIME1 extends Regime
  case object REGIME2 extends Regime
  implicit val regimeRead: Reads[Regime] = {
    __.read[String].filter(ValidationError("""Valeurs possibles "1" ou "2" """))(List("1", "2").contains(_)) map {
      case "1" => REGIME1
      case "2" => REGIME2
    }
  }
}