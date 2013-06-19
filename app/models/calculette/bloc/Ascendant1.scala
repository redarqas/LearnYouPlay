package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Ascendant1 {
  val readAsc1_dt_nais: Reads[Option[org.joda.time.DateTime]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc1_dt_nais").readNullable[org.joda.time.DateTime]).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  val readAsc1_regime: Reads[Option[models.calculette.Regime]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc1_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["1","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readModule_asc1: Reads[Option[String]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "module_asc1").readNullable[String]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["1","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  implicit val readAscendant1 = (readAsc1_dt_nais and readAsc1_regime and readModule_asc1)((r,s,t) => Map("asc1_dt_nais"-> r, "asc1_regime" -> s, "module_asc1" ->t))
}
