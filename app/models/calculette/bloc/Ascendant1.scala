package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Ascendant1 {

  val readAsc1_dt_nais = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc1_dt_nais").readNullable[org.joda.time.DateTime]).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("asc1_dt_nais" -> _._2.map(_.toString("dd/MM/yyyy")))

  val readAsc1_regime = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc1_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["1","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("asc1_regime" -> _._2.map(_.toString()))

  val readModule_asc1 = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "module_asc1").readNullable[String]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["1","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("module_asc1" -> _._2)

  implicit val readAscendant1 = (readAsc1_dt_nais and readAsc1_regime and readModule_asc1)(Map(_, _, _))
}
