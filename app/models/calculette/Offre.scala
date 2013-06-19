package models.calculette

import play.api.libs.json._

sealed class Offre

object Offre {

  case object OFFRE_01 extends Offre
  case object OFFRE_02 extends Offre
  case object OFFRE_03 extends Offre
  case object OFFRE_04 extends Offre
  case object OFFRE_05 extends Offre
  case object OFFRE_06 extends Offre
  case object OFFRE_07 extends Offre

  implicit val offreRead : Reads[Offre] = {
    __.read[String] map {
      case "01" => OFFRE_01
      case "02" => OFFRE_02
      case "03" => OFFRE_03
      case "04" => OFFRE_04
      case "05" => OFFRE_05
      case "06" => OFFRE_06
      case "07" => OFFRE_07
    }
  }

}