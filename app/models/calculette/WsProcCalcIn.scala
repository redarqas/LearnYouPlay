package models.calculette

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object WsProcCalcIn {
  //Conjoint 

  //Ascendant 1  : Date naissance / Regime / Module 
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

  val readModule_asc1: Reads[Option[models.calculette.Regime]] = (
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "module_asc1").readNullable[models.calculette.Regime]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["1","2"] """))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  //Ascendant 2  : Date naissance / Regime / Module 
  val readAsc2_dt_nais = (__ \ "asc2_dt_nais").readNullable[org.joda.time.DateTime]
  val readAsc2_regime: Reads[Option[models.calculette.Regime]] = (
    (__ \ "asc2_dt_nais").readNullable[org.joda.time.DateTime] and
    (__ \ "asc2_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError("""requis si asc2_dt_nais est alimentée,valeurs possibles ["1","2"]"""))(r => {
      r match {
        case (Some(_), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  val readModule_asc2: Reads[Option[String]] = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc2_dt_nais").readNullable[org.joda.time.DateTime] and
    (__ \ "module_asc2").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), Some(models.calculette.Top.OUI), Some(_), Some(_)) => true
        case (Some(models.calculette.Garantie.GARANTIE_AM), Some(models.calculette.Top.OUI), Some(_), Some(_)) => true
        case _ => false
      }
    }) map (_._4)
  //Ascendant 2  : Date naissance / Regime / Module 
  val readAsc3_dt_nais = (__ \ "asc3_dt_nais").readNullable[org.joda.time.DateTime]

  val readAsc3_regime: Reads[Option[models.calculette.Regime]] = (
    (__ \ "asc3_dt_nais").readNullable[org.joda.time.DateTime] and
    (__ \ "asc3_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError("""requis si asc3_dt_nais est alimentée,valeurs possibles ["1","2"]"""))(r => {
      r match {
        case (Some(_), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readModule_asc3: Reads[Option[String]] = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc3_dt_nais").readNullable[org.joda.time.DateTime] and
    (__ \ "module_asc3").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), Some(models.calculette.Top.OUI), Some(_), Some(_)) => true
        case (Some(models.calculette.Garantie.GARANTIE_AM), Some(models.calculette.Top.OUI), Some(_), Some(_)) => true
        case _ => false
      }
    }) map (_._4)
  //Ascendant 4
  val readAsc4_dt_nais = (__ \ "asc4_dt_nais").readNullable[org.joda.time.DateTime]
  val readAsc4_regime: Reads[Option[models.calculette.Regime]] = (
    (__ \ "asc3_dt_nais").readNullable[org.joda.time.DateTime] and
    (__ \ "asc3_regime").readNullable[models.calculette.Regime]).tupled.filter(ValidationError("""requis si asc4_dt_nais est alimentée,valeurs possibles ["1","2"]"""))(r => {
      r match {
        case (Some(_), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readModule_asc4: Reads[Option[String]] = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "top_infosasc").readNullable[models.calculette.Top] and
    (__ \ "asc4_dt_nais").readNullable[org.joda.time.DateTime] and
    (__ \ "module_asc4").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), Some(models.calculette.Top.OUI), Some(_), Some(_)) => true
        case (Some(models.calculette.Garantie.GARANTIE_AM), Some(models.calculette.Top.OUI), Some(_), Some(_)) => true
        case _ => false
      }
    }) map (_._4)

  //Enfants

}

class WsProcCalcIn(

  /** ----------------------------------- **/
  /** Obligatoire                         **/
  /** ----------------------------------- **/
  /** requis, format = "JJ/MM/AAAA" **/
  mp_dt_nais: org.joda.time.DateTime,
  /** requis, valeurs possibles ["1","2"] **/
  mp_regime: models.calculette.Regime,
  /** requis, valeurs possible ["O", "N"] **/
  mp_aphp: models.calculette.Top,
  /** requis, valeurs possible ["O", "N"] **/
  top_infoscj: models.calculette.Top,
  /** requis, valeurs possible ["O", "N"] **/
  top_infosenf: models.calculette.Top,
  /** requis, valeurs possible ["O", "N"] **/
  top_infosasc: models.calculette.Top,
  /** requis, valeurs possibles ["4G", "2B", "P1", "ET", "LB", "G", "AB", "AM", "AS"] **/
  garantie: models.calculette.Garantie,
  /** requis, valeurs possibles [01,02,03,05,06,07] **/
  offre: models.calculette.Offre,
  /** requis, format MMAAAA **/
  aa_effet: String,
  /** requis, format = "AAAA", si pas d'mp_anc alors extraire l'année depuis aa_effet **/
  mp_aa_adh: String,
  /** ----------------------------------- **/
  /** Obligatoire si top_infoscj          **/
  /** ----------------------------------- **/
  /** requis si top_infoscj = 'O', format "JJ/MM/AAAA" **/
  cj_dt_nais: Option[DateTime],
  /** requis si top_infoscj = 'O', format "AAAA" **/
  cj_aa_adh: Option[String],
  /** requis si top_infoscj = 'O', valeurs possibles ["1","2"] **/
  cj_regime: Option[models.calculette.Regime],
  /** requis si top_infoscj = 'O', valeurs possible ["O", "N"] **/
  cj_aphp: Option[models.calculette.Top],
  /** requis si top_infosasc = 'O', format "JJ/MM/AAAA" **/
  asc1_dt_nais: Option[DateTime],
  /** requis si top_infosasc = 'O', valeurs possibles ["1","2"] **/
  asc1_regime: Option[models.calculette.Regime],
  /** requis, valeurs possible ["O", "N"] **/
  mp_prev: models.calculette.Top,
  /** requis si top_infoscj = 'O', valeurs possible ["O", "N"] **/
  cj_prev: Option[models.calculette.Top],
  /** ----------------------------------- **/
  /** Obligatoire si top_infosenf         **/
  /** ----------------------------------- **/
  /** requis si top_infosenf = 'O', nombre sur deux caractéres **/
  nb_enf_rg: String,
  /** requis si top_infosenf = 'O', nombre sur deux caractéres **/
  nb_enf_am: String,
  /** requis si top_infosenf = 'O', nombre sur deux caractéres **/
  nb_enf_ar: String,
  /** ----------------------------------- **/
  /** Obligatoire si Garantie             **/
  /** ----------------------------------- **/
  /** requis si garantie = 'P1' ou 'AM' **/
  module_mp: String,
  /** requis si garantie = 'P1' ou 'AM' et top_infoscj = 'O' **/
  module_cj: String,
  /** requis si garantie = 'P1' ou 'AM' et top_infosenf = 'O' **/
  module_enf: String,
  /** requis si garantie = 'P1' ou 'AM' et top_infosasc = 'O' **/
  module_asc1: String,
  /** requis si garantie = 'P1' ou 'AM' et top_infosasc = 'O' et asc2_dt_nais est alimentée  **/
  module_asc2: String,
  /** requis si garantie = 'P1' ou 'AM' et top_infosasc = 'O' et asc3_dt_nais est alimentée **/
  module_asc3: String,
  /** requis si garantie = 'P1' ou 'AM' et top_infosasc = 'O' et asc4_dt_nais est alimentée **/
  module_asc4: String,

  /** format = "JJ/MM/AAAA" **/
  asc2_dt_nais: String,
  /** requis si asc2_dt_nais est alimentée, valeurs possibles ["1","2"] **/
  asc2_regime: models.calculette.Regime,
  /** format = "JJ/MM/AAAA" **/
  asc3_dt_nais: String,
  /** requis si asc3_dt_nais est alimentée, valeurs possibles ["1","2"] **/
  asc3_regime: models.calculette.Regime,
  /** format = "JJ/MM/AAAA" **/
  asc4_dt_nais: org.joda.time.DateTime,
  /** requis si asc4_dt_nais est alimentée **/
  asc4_regime: String,

  /** valeurs possibles [ "00", "05", "15", "25"] **/
  taux_mino: models.calculette.TauxMino,
  /** longueur 2 **/
  mp_anc: String,
  /** longueur 2 **/
  cj_anc: String,
  /** longueur 14 **/
  num_id: String,
  /** longueur 14 **/
  num_devis: String)
  
  
  
  
