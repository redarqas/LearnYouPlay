package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

case class MembrePrincipal(
  mp_dt_nais: org.joda.time.DateTime,
  mp_regime: models.calculette.Regime,
  mp_aphp: models.calculette.Top,
  garantie: models.calculette.Garantie,
  offre: models.calculette.Offre,
  aa_effet: String,
  mp_aa_adh: String,
  mp_prev: String,
  top_infoscj: models.calculette.Top,
  top_infosenf: models.calculette.Top,
  top_infosasc: models.calculette.Top,
  taux_mino: Option[models.calculette.TauxMino],
  mp_anc: Option[String],
  cj_anc: Option[String],
  num_id: Option[String],
  num_devis: Option[String],
  module_mp: Option[String])

object MembrePrincipal {

  
  
  val test =(__ \ "mp_regime").read[models.calculette.Regime].map(r => "mp_regime" -> Option(r.toString)) 
  
  val readBasics = (__ \ "mp_dt_nais").read[Option[org.joda.time.DateTime]](jodaDateReads("dd/MM/yyyy") map (Option(_))) and
    (__ \ "mp_regime").read[models.calculette.Regime].map(r => "mp_regime" -> Option(r.toString)) and
    (__ \ "mp_aphp").read[Option[models.calculette.Top]] and
    (__ \ "garantie").read[Option[models.calculette.Garantie]] and
    (__ \ "offre").read[Option[models.calculette.Offre]] and
    (__ \ "aa_effet").read[Option[org.joda.time.DateTime]](jodaDateReads("MMyyyy") map (Option(_))) and
    (__ \ "mp_prev").read[Option[String]] and
    (__ \ "top_infoscj").read[Option[models.calculette.Top]] and
    (__ \ "top_infosenf").read[Option[models.calculette.Top]] and
    (__ \ "top_infosasc").read[Option[models.calculette.Top]] and
    (__ \ "taux_mino").readNullable[models.calculette.TauxMino] and
    (__ \ "mp_anc").readNullable[String] and
    (__ \ "cj_anc").readNullable[String] and
    (__ \ "num_id").readNullable[String] and
    (__ \ "num_devis").readNullable[String]

  val readMp_aa_adh = ((__ \ "aa_effet").read[org.joda.time.DateTime](jodaDateReads("MMyyyy")) and
    (__ \ "mp_aa_adh").readNullable[org.joda.time.DateTime](jodaDateReads("yyyy"))).tupled.map(r => r match {
      case (d, None) => Some(d.toString("yyyy"))
      case (_, Some(a)) => Some(a.toString("yyyy"))
    })
    
  val readValidModule_mp: Reads[Option[String]] = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "module_mp").readNullable[String]).tupled.filter(ValidationError("module_mp requis si garantie est à 'P1' ou 'AM'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), None) => false
        case (Some(models.calculette.Garantie.GARANTIE_AM), None) => false
        case _ => true
      }
    }) map (_._2)
  
  implicit val readMembrePrincipal = (readBasics and readMp_aa_adh and readValidModule_mp)((
    mp_dt_nais,
    mp_regime,
    mp_aphp,
    garantie,
    offre,
    mp_aa_adh,
    mp_prev,
    top_infoscj,
    top_infosenf,
    top_infosasc,
    taux_mino,
    mp_anc,
    cj_anc,
    num_id,
    num_devis,
    aa_effet,
    module_mp) => Map[String, Option[String]]("mp_dt_nais" -> mp_dt_nais,
    "mp_regime" -> mp_regime,
    "mp_aphp" -> mp_aphp,
    "garantie" -> garantie,
    "offre" -> offre,
    "mp_aa_adh" -> mp_aa_adh,
    "mp_prev" -> mp_prev,
    "top_infoscj" -> top_infoscj,
    "top_infosenf" -> top_infosenf,
    "top_infosasc" -> top_infosasc,
    "taux_mino" -> taux_mino,
    "mp_anc" -> mp_anc,
    "cj_anc" -> cj_anc,
    "num_id" -> num_id,
    "num_devis" -> num_devis,
    "aa_effet" -> aa_effet,
    "module_mp" -> module_mp))

}