package models.calculette

import play.api.libs.json._

sealed trait Top

object Top {
  case object OUI extends Top
  case object NON extends Top
  
  implicit val topRead: Reads[Top] = {
    __.read[String] map {
      case "O" => OUI
      case "N" => OUI
    }
  }
} 