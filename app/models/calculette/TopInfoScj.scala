package models.calculette

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

case class TopInfoScj(cj_dt_nais: Option[org.joda.time.DateTime],
  cj_aa_adh: Option[String],
  cj_regime: Option[models.calculette.Regime],
  cj_aphp: Option[models.calculette.Top],
  cj_prev: Option[models.calculette.Top])

object TopInfoScj {

  val readCj_dt_nais: Reads[Option[org.joda.time.DateTime]] = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_dt_nais").readNullable[org.joda.time.DateTime]).tupled.filter(ValidationError("requis si top_infoscj = 'O', format JJ/MM/AAAA"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readCj_aa_adh: Reads[Option[String]] = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_aa_adh").readNullable[String]).tupled.filter(ValidationError("requis si top_infoscj = 'O', format AAAA"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readCj_regime: Reads[Option[models.calculette.Regime]] = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError("""requis si top_infoscj = 'O', valeurs possibles ["1","2"]"""))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readCj_aphp: Reads[Option[models.calculette.Top]] = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_aphp").readNullable[models.calculette.Top]).tupled.filter(ValidationError("""requis si top_infoscj = 'O', valeurs possible ["O", "N"]"""))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
    
  val readCj_prev: Reads[Option[models.calculette.Top]] = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_prev").readNullable[models.calculette.Top]).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
    
  implicit val readTopInfoScj: Reads[TopInfoScj] = (readCj_dt_nais and readCj_aa_adh and readCj_regime and readCj_aphp and readCj_prev)(TopInfoScj.apply _)

}