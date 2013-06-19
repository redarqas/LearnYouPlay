package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Ascendant4 {

  val readAsc4_dt_nais: Reads[Option[org.joda.time.DateTime]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc4_dt_nais").readNullable[org.joda.time.DateTime]).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  val readAsc4_regime: Reads[Option[models.calculette.Regime]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc4_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["4","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readModule_asc4: Reads[Option[String]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "module_asc4").readNullable[String]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["4","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
    
  implicit val readAscendant4 = (readAsc4_dt_nais and readAsc4_regime and readModule_asc4)((r,s,t) => Map("asc4_dt_nais"-> r, "asc4_regime" -> s, "module_asc4" ->t))
}
