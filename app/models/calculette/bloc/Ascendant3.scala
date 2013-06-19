package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Ascendant3 {

  val readAsc3_dt_nais = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc3_dt_nais").readNullable[org.joda.time.DateTime]).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("asc3_dt_nais" -> _._2.map(_.toString("dd/MM/yyyy")))

  val readAsc3_regime = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc3_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["3","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("asc3_regime" -> _._2.map(_.toString()))

  val readModule_asc3 = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "module_asc3").readNullable[String]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["3","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("module_asc3" -> _._2)

  implicit val readAscendant3 = (readAsc3_dt_nais and readAsc3_regime and readModule_asc3)(Map(_, _, _))
}
