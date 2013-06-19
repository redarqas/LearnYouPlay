package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

case class Ascendant3(asc3_dt_nais: Option[org.joda.time.DateTime],
  asc3_regime: Option[models.calculette.Regime],
  module_asc3: Option[String])

object Ascendant3 {

  val readAsc3_dt_nais: Reads[Option[org.joda.time.DateTime]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc3_dt_nais").readNullable[org.joda.time.DateTime]).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  val readAsc3_regime: Reads[Option[models.calculette.Regime]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc3_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["3","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readModule_asc3: Reads[Option[String]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "module_asc3").readNullable[String]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["3","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  implicit val readAscendant3 = (readAsc3_dt_nais and readAsc3_regime and readModule_asc3)((r,s,t) => Map("asc3_dt_nais"-> r, "asc3_regime" -> s, "module_asc3" ->t))
}
