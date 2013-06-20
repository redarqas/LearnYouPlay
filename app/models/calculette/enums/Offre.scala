package models.calculette.enums

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError

sealed trait Offre 
object Offre {

  case object OFFRE_01 extends Offre
  case object OFFRE_02 extends Offre
  case object OFFRE_03 extends Offre
  case object OFFRE_04 extends Offre
  case object OFFRE_05 extends Offre
  case object OFFRE_06 extends Offre
  case object OFFRE_07 extends Offre

  implicit val offreRead: Reads[Offre] = {
    __.read[String].filter(ValidationError("""Valeurs possibles ["01","02","03","04","05","06","07"] """))(List("01", "02", "03", "04", "05", "06", "07").contains(_)) map {
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