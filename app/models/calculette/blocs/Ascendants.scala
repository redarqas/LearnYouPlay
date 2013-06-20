package models.calculette.blocs

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError
import org.joda.time.DateTime

object Ascendants {

  val readAsc_dt_nais = (num: Int) => (
    ((__ \ "top_infosasc").readNullable[models.calculette.enums.Top] ) and
    ((__ \ s"asc${num}_dt_nais").readNullable[String] keepAnd
     (__ \ s"asc${num}_dt_nais").readNullable[org.joda.time.DateTime](jodaDateReads("dd/MM/yyyy")))).tupled.filter(ValidationError("""requis si top_infosasc = 'O', format "JJ/MM/AAAA" """))(r => {
        r match {
          case (Some(models.calculette.enums.Top.OUI), Some(_)) => true
          case _ => false
        }
      }) map (x => s"asc${num}_dt_nais" -> x._2) 

  val readAsc_regime = (num: Int) => (
    ((__ \ "top_infosasc").readNullable[models.calculette.enums.Top] ) and
    ((__ \ s"asc${num}_regime").readNullable[String] keepAnd 
     (__ \ s"asc${num}_regime").readNullable[models.calculette.enums.Regime])).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["1","2"] """))(r => {
      r match {
        case (Some(models.calculette.enums.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (x => s"asc${num}_regime" -> x._2)

  val readModule_asc = (num: Int) => (
    ((__ \ "top_infosasc").readNullable[models.calculette.enums.Top] ) and
    (__ \ s"module_asc${num}").readNullable[String]).tupled.filter(ValidationError(""" requis si top_infosasc = 'O', valeurs possibles ["1","2"] """))(r => {
      r match {
        case (Some(models.calculette.enums.Top.OUI), Some(_)) => true
        case _ => false
      }
    }) map (x => s"module_asc${num}" -> x._2)
    
  //TODO : Reduce this  
  implicit val readAscendants = (readAsc_dt_nais(1) and readAsc_regime(1) and readModule_asc(1) and
  readAsc_dt_nais(2) and readAsc_regime(2) and readModule_asc(2) and 
  readAsc_dt_nais(3) and readAsc_regime(3) and readModule_asc(3) and
  readAsc_dt_nais(4) and readAsc_regime(4) and readModule_asc(4)) (Map(_, _, _,_, _, _,_, _, _,_, _, _))
}
