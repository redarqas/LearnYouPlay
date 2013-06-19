package models.calculette

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

case class TopInfosEnf(
  nb_enf_rg: Option[String],
  nb_enf_am: Option[String],
  nb_enf_ar: Option[String])

object TopInfosEnf {
  
  val readNb_enf_rg: Reads[Option[String]] = (
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "nb_enf_rg").readNullable[String]).tupled.filter(ValidationError("nb_enf_rg requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)

  val readNb_enf_am: Reads[Option[String]] = (
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "nb_enf_am").readNullable[String]).tupled.filter(ValidationError("nb_enf_am requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  val readNb_enf_ar: Reads[Option[String]] = (
    (__ \ "top_infosenf").readNullable[models.calculette.Top] and
    (__ \ "nb_enf_ar").readNullable[String]).tupled.filter(ValidationError("nb_enf_ar requis si top_infosenf est à 'O'"))(r => {
      r match {
        case (Some(models.calculette.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (_._2)
  implicit val readTopInfosEnf = (readNb_enf_rg and readNb_enf_am and readNb_enf_ar) (TopInfosEnf.apply _)
}