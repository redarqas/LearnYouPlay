package models.calculette
import play.api.libs.json._

sealed class TauxMino

object TauxMino {
  case object TAUXMINO_00 extends TauxMino
  case object TAUXMINO_05 extends TauxMino
  case object TAUXMINO_15 extends TauxMino
  case object TAUXMINO_25 extends TauxMino
  
  implicit val tauxMinoRead : Reads[TauxMino] = {
    __.read[String] map {
      case "00" => TauxMino.TAUXMINO_00
      case "05" => TauxMino.TAUXMINO_05
      case "15" => TauxMino.TAUXMINO_15
      case "25" => TauxMino.TAUXMINO_25
    }
  }
  
}