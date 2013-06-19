package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Conjoint {

  val readCj_dt_nais = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_dt_nais").readNullable[org.joda.time.DateTime]).tupled.filter(ValidationError("requis si top_infoscj = 'O', format JJ/MM/AAAA"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("cj_dt_nais" -> _._2.map(_.toString("dd/MM/yyyy")))

  val readCj_aa_adh = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_aa_adh").readNullable[String]).tupled.filter(ValidationError("requis si top_infoscj = 'O', format AAAA"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("cj_aa_adh" -> _._2)

  val readCj_regime = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError("""requis si top_infoscj = 'O', valeurs possibles ["1","2"]"""))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("cj_regime" -> _._2.map(_.toString()))

  val readCj_aphp = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_aphp").readNullable[models.calculette.Top]).tupled.filter(ValidationError("""requis si top_infoscj = 'O', valeurs possible ["O", "N"]"""))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("cj_aphp" -> _._2.map(_.toString()))

  val readCj_prev = (
    (__ \ "top_infoscj").readNullable[models.calculette.Top] and
    (__ \ "cj_prev").readNullable[models.calculette.Top]).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("cj_prev" -> _._2.map(_.toString()))

  val readModule_cj = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "module_cj").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), Some(models.calculette.Top.OUI), None) => false
        case (Some(models.calculette.Garantie.GARANTIE_AM), Some(models.calculette.Top.OUI), None) => false
        case _ => true
      }
    }) map ("module_cj" -> _._3)

  implicit val readConjoint = (readCj_dt_nais and readCj_aa_adh and readCj_regime and readCj_aphp and readCj_prev and readModule_cj)(Map(_, _, _, _, _, _))

}