package models.calculette
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError

sealed trait TauxMino {
  override def toString() = this match {
    case TauxMino.TAUXMINO_00 => "00"
    case TauxMino.TAUXMINO_05 => "05"
    case TauxMino.TAUXMINO_15 => "15"
    case TauxMino.TAUXMINO_25 => "25"
  }
}

object TauxMino {
  case object TAUXMINO_00 extends TauxMino
  case object TAUXMINO_05 extends TauxMino
  case object TAUXMINO_15 extends TauxMino
  case object TAUXMINO_25 extends TauxMino

  implicit val tauxMinoRead: Reads[TauxMino] = {
    __.read[String].filter(ValidationError("""Valeurs possibles ["00","O5","15","25"] """))(List("00", "O5", "15", "25").contains(_)) map {
      case "00" => TauxMino.TAUXMINO_00
      case "05" => TauxMino.TAUXMINO_05
      case "15" => TauxMino.TAUXMINO_15
      case "25" => TauxMino.TAUXMINO_25
    }
  }

}