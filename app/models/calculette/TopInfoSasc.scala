package models.calculette
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

case class TopInfoSasc(asc1_dt_nais: Option[DateTime],
  asc1_regime: Option[models.calculette.Regime])

object TopInfoSasc {

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

  implicit val readTopInfoSasc = (readAsc1_dt_nais and readAsc1_regime)(TopInfoSasc.apply _)

}