package models.calculette

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime
import models.calculette.bloc._
import MembrePrincipal._
import Conjoint._
import Enfants._
import Ascendant1._
import Ascendant2._
import Ascendant3._
import Ascendant4._

case class CalculCotisations(fields: Map[String, Option[Object]]) {
  val queryString = fields.filter(r => r match {
    case (_, Some(_)) => true
    case _ => false
  }).map(a => a._1 + "=" + a._2.get).mkString("\n&")
}

object CalculCotisations {
  val readCalculCotisations = (readMembrePrincipal and
    readConjoint and
    readEnfants and
    readAscendant1 and
    readAscendant2 and
    readAscendant3 and
    readAscendant4)((mp, cj, enf, asc1, asc2, asc3, asc4) => CalculCotisations(mp ++ cj ++ enf ++ asc1 ++ asc2 ++ asc3 ++ asc4))
}