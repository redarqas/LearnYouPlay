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

  val readBasics = (__ \ "mp_dt_nais").read[org.joda.time.DateTime](jodaDateReads("dd/MM/yyyy")).map(d => "mp_dt_nais" -> Option(d.toString("dd/MM/yyyy"))) and
    (__ \ "mp_regime").read[models.calculette.Regime].map(r => "mp_regime" -> Option(r.toString)) and
    (__ \ "mp_aphp").read[models.calculette.Top].map(r => "mp_aphp" -> Option(r.toString)) and
    (__ \ "garantie").read[models.calculette.Garantie].map(r => "garantie" -> Option(r.toString)) and
    (__ \ "offre").read[models.calculette.Offre].map(r => "offre" -> Option(r.toString)) and
    (__ \ "aa_effet").read[org.joda.time.DateTime](jodaDateReads("MMyyyy")).map(d => "aa_effet" -> Option(d.toString("MMyyyy"))) and
    (__ \ "mp_prev").read[String].map("mp_prev" -> Option(_)) and
    (__ \ "top_infoscj").read[models.calculette.Top].map(r => "top_infoscj" -> Option(r.toString)) and
    (__ \ "top_infosenf").read[models.calculette.Top].map(r => "top_infosenf" -> Option(r.toString)) and
    (__ \ "top_infosasc").read[models.calculette.Top].map(r => "top_infosasc" -> Option(r.toString)) and
    (__ \ "taux_mino").readNullable[models.calculette.TauxMino].map("taux_mino" -> _.map(_.toString)) and
    (__ \ "mp_anc").readNullable[String].map("mp_anc" -> _) and
    (__ \ "cj_anc").readNullable[String].map("cj_anc" -> _) and
    (__ \ "num_id").readNullable[String].map("num_id" -> _) and
    (__ \ "num_devis").readNullable[String].map("num_devis" -> _)

  val readMp_aa_adh: Reads[(String, Option[String])] = ((__ \ "aa_effet").read[org.joda.time.DateTime](jodaDateReads("MMyyyy")) and
    (__ \ "mp_aa_adh").readNullable[org.joda.time.DateTime](jodaDateReads("yyyy"))).tupled.map(r => r match {
      case (d, None) => "mp_aa_adh" -> Option(d.toString("yyyy"))
      case (_, Some(a)) => "mp_aa_adh" -> Option(a.toString("yyyy"))
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