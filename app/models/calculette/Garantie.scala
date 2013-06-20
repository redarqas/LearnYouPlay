package models.calculette

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError

sealed trait Garantie {
  override def toString() = {
    this match {
      case Garantie.GARANTIE_4G => "4G"
      case Garantie.GARANTIE_2B => "2B"
      case Garantie.GARANTIE_P1 => "P1"
      case Garantie.GARANTIE_ET => "ET"
      case Garantie.GARANTIE_LB => "LB"
      case Garantie.GARANTIE_G  => "G"
      case Garantie.GARANTIE_AB => "AB"
      case Garantie.GARANTIE_AM => "AM"
      case Garantie.GARANTIE_AS => "AS"
    }
  }
}

object Garantie {

  case object GARANTIE_4G extends Garantie
  case object GARANTIE_2B extends Garantie
  case object GARANTIE_P1 extends Garantie
  case object GARANTIE_ET extends Garantie
  case object GARANTIE_LB extends Garantie
  case object GARANTIE_G extends Garantie
  case object GARANTIE_AB extends Garantie
  case object GARANTIE_AM extends Garantie
  case object GARANTIE_AS extends Garantie

  implicit val garantieRead: Reads[Garantie] = {
    __.read[String].filter(ValidationError("""Valeurs possibles ["4G","2B","P1","ET","LB","G","AB","AM","AS"] """))(List("4G", "2B", "P1", "ET", "LB", "G", "AB", "AM", "AS").contains(_)) map {
      case "4G" => GARANTIE_4G
      case "2B" => GARANTIE_2B
      case "P1" => GARANTIE_P1
      case "ET" => GARANTIE_ET
      case "LB" => GARANTIE_LB
      case "G" => GARANTIE_G
      case "AB" => GARANTIE_AB
      case "AM" => GARANTIE_AM
      case "AS" => GARANTIE_AS
    }
  }

}