package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Conjoint {
  
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

  val readModule_cj: Reads[Option[String]] = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "module_cj").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), Some(models.calculette.Top.OUI), None) => false
        case (Some(models.calculette.Garantie.GARANTIE_AM), Some(models.calculette.Top.OUI), None) => false
        case _ => true
      }
    }) map (_._3)


    
    implicit val readConjoint = (readCj_dt_nais and readCj_aa_adh and readCj_regime and readCj_aphp and readCj_prev and readModule_cj)((a,b,c,d,e,f) => Map("cj_dt_nais" -> a, "cj_aa_adh"-> b, "cj_regime"-> c, "cj_aphp"-> d , "cj_prev"-> e, "module_cj" -> e))
  
}