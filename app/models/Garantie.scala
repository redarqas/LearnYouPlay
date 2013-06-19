package models

import play.api.libs.json._

sealed class Garantie

object Garantie {

  case object GARANTIE_4G extends Garantie
  case object GARANTIE_2B extends Garantie
  case object GARANTIE_P1 extends Garantie
  case object GARANTIE_ET extends Garantie
  case object GARANTIE_LB extends Garantie
  case object GARANTIE_G  extends Garantie
  case object GARANTIE_AB extends Garantie
  case object GARANTIE_AM extends Garantie
  case object GARANTIE_AS extends Garantie

  implicit val offreRead : Reads[Garantie] = {
    __.read[String] map {
      case "4G" => GARANTIE_4G
      case "2B" => GARANTIE_2B
      case "P1" => GARANTIE_P1
      case "ET" => GARANTIE_ET
      case "LB" => GARANTIE_LB
      case "G"  => GARANTIE_G
      case "AB" => GARANTIE_AB
      case "AM" => GARANTIE_AM
      case "AS" => GARANTIE_AS
    }
  }

}