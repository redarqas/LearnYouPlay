package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime


object MembrePrincipal {

  val readBasics = 
    ((__ \ "mp_dt_nais").read[Option[String]] keepAnd 
     (__ \ "mp_dt_nais").read[org.joda.time.DateTime](jodaDateReads("dd/MM/yyyy"))).map("mp_dt_nais" -> _) and
    ((__ \ "mp_regime").read[Option[String]] keepAnd 
     (__ \ "mp_regime").read[models.calculette.Regime]).map("mp_regime" -> _) and
    ((__ \ "mp_aphp").read[Option[String]] keepAnd 
     (__ \ "mp_aphp").read[models.calculette.Top]).map("mp_aphp" -> _) and
    ((__ \ "garantie").read[Option[String]] keepAnd
     (__ \ "garantie").read[models.calculette.Garantie]).map("garantie" -> _) and
    ((__ \ "offre").read[Option[String]] keepAnd 
     (__ \ "offre").read[models.calculette.Offre]).map("offre" -> _) and
    ((__ \ "aa_effet").read[Option[String]] keepAnd
     (__ \ "aa_effet").read[org.joda.time.DateTime](jodaDateReads("MMyyyy"))).map("aa_effet" -> _) and
    (__ \ "mp_prev").read[Option[String]].map("mp_prev" -> _) and
    ((__ \ "top_infoscj").read[Option[String]] keepAnd
     (__ \ "top_infoscj").read[models.calculette.Top]).map("top_infoscj" -> _) and
    ((__ \ "top_infosenf").read[Option[String]] keepAnd
     (__ \ "top_infosenf").read[models.calculette.Top]).map( "top_infosenf" -> _) and
    ((__ \ "top_infosasc").read[Option[String]] keepAnd
     (__ \ "top_infosasc").read[models.calculette.Top]).map("top_infosasc" -> _) and
    ((__ \ "taux_mino").readNullable[String] keepAnd
     (__ \ "taux_mino").readNullable[models.calculette.TauxMino]).map("taux_mino" -> _) and
    (__ \ "mp_anc").readNullable[String].map("mp_anc" -> _) and
    (__ \ "cj_anc").readNullable[String].map("cj_anc" -> _) and
    (__ \ "num_id").readNullable[String].map("num_id" -> _) and
    (__ \ "num_devis").readNullable[String].map("num_devis" -> _)

  val readMp_aa_adh: Reads[(String, Option[String])] = ((__ \ "aa_effet").read[org.joda.time.DateTime](jodaDateReads("MMyyyy")) and
    ((__ \ "mp_aa_adh").readNullable[String] keepAnd (__ \ "mp_aa_adh").readNullable[org.joda.time.DateTime](jodaDateReads("yyyy")))
    ).tupled.map(r => r match {
      case (d, None) => "mp_aa_adh" -> Option(d.toString("yyyy"))
      case (_, e) => "mp_aa_adh" -> e
    })

  val readModule_mp: Reads[(String, Option[String])] = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "module_mp").readNullable[String]).tupled.filter(ValidationError("module_mp requis si garantie est à 'P1' ou 'AM'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), None) => false
        case (Some(models.calculette.Garantie.GARANTIE_AM), None) => false
        case _ => true
      }
    }) map ("module_mp" -> _._2)

  implicit val readMembrePrincipal = (readBasics and readMp_aa_adh and readModule_mp)(Map(_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _))
}