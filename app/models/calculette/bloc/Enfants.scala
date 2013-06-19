package models.calculette.bloc

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Enfants {

  val readNb_enf_rg = (
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "nb_enf_rg").readNullable[String]).tupled.filter(ValidationError("nb_enf_rg requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("nb_enf_rg" -> _._2)

  val readNb_enf_am = (
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "nb_enf_am").readNullable[String]).tupled.filter(ValidationError("nb_enf_am requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("nb_enf_am" -> _._2)
  val readNb_enf_ar = (
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "nb_enf_ar").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map ("nb_enf_ar" -> _._2)

  val readModule_enf = (
    (__ \ "garantie").readNullable[models.calculette.Garantie] and
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "module_enf").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Garantie.GARANTIE_P1), Some(models.calculette.Top.OUI), None) => false
        case (Some(models.calculette.Garantie.GARANTIE_AM), Some(models.calculette.Top.OUI), None) => false
        case _ => true
      }
    }) map ("module_enf" -> _._3)

  implicit val readEnfants = (readNb_enf_rg and readNb_enf_am and readNb_enf_ar and readModule_enf)(Map(_, _, _, _))
}