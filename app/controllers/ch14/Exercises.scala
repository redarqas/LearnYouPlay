package controllers.ch14

import play.api.mvc.Controller
import play.api.libs.json.JsValue
import play.api.libs.json.JsNumber
import play.api.libs.json.JsString
import play.api.libs.json.JsPath
import play.api.libs.json.Json
import play.api.libs.json.ConstraintReads
import play.api.mvc._
import play.api.data._
import play.api.libs.iteratee.Enumerator
import play.api.data.validation._
import views.html.defaultpages.badRequest
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.Reads
import models.Contractor
import models.calculette.CalculCotisations
import models.calculette.bloc.MembrePrincipal
import models.calculette.bloc.Enfants
import models.calculette.bloc.Conjoint
import models.calculette.bloc.Ascendant1
import models.calculette.bloc.Ascendant2
import models.calculette.bloc._

//Json Object : name / value
//value are : string, number, Json Object, Json array, true/false, null
//Equivalent types : JsString,  JsNumber, JsObject, JsArray, JsBoolean, JsNull
//Added Type : JsUndefined (Internal usage)
//Super Type : JsValue

object Exercises extends Controller {

  //Parse a string
  val parsedString: JsValue = Json.parse(
    """
      { "product": {
           "id": 123,
           "name": "Patates",
           "price": 1.75
          }
      }
      """)
  def serveParsedJsonString = Action {
    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "application/json")),
      body = Enumerator(parsedString))
  }

  //Raw json
  val rawJson = Json.obj(
    "products" -> Json.arr(
      Json.obj(
        "id" -> JsNumber(1),
        "name" -> JsString("pommes"),
        "price" -> JsNumber(1.23)),
      Json.obj(
        "id" -> JsNumber(2),
        "name" -> JsString("bananes"),
        "price" -> JsNumber(1.23))))

  def serveRawJson = Action { implicit request =>
    render {
      case Accepts.Json() => Ok(rawJson)
    }
  }
  //Serialize Json to String 
  def servePrettyJsonString = Action {
    Ok(Json.prettyPrint(rawJson))
  }

  //Json navigation : Simple path
  def productName = Action {
    val firstProdcutName: JsValue = (JsPath \ "product" \ "name") asSingleJson (parsedString)
    Ok(firstProdcutName)
  }
  //Json navigation : Recursive path
  def productsNames = Action {
    val productNames: Seq[JsValue] = rawJson \ "products" \\ "name"
    SimpleResult(
      header = ResponseHeader(200),
      body = Enumerator(productNames: _*))
  }

  //Option: Json safe conversion to scala value : using asOpt
  def convertToScalaVal = Action {
    val name: Option[String] = (parsedString \ "product" \ "name").asOpt[String]
    val nameNotFound: Option[String] = (parsedString \ "product" \ "propertyNotInJson").asOpt[String]
    val conversionNotPossible: Option[Long] = (parsedString \ "product" \ "propertyNotPresent").asOpt[Long]
    Ok(views.html.ch14.convertToScalaVal.render(name, nameNotFound, conversionNotPossible))
  }

  //Either : Json safe detailed conversion to scala value : using validate 
  //JsResult = JsSuccess | JsError
  def validateOk = Action {
    val name = (parsedString \ "product" \ "name").validate[String].fold(
      errors => "There is validation errors",
      s => s)
    Ok(name)
  }

  def validateNotFound = Action {
    val propertyNotPresent = (parsedString \ "product" \ "propertyNotPresent").validate[String].fold(
      errors => "Path not found",
      s => s)
    Ok(propertyNotPresent)
  }

  def validateConvertNotPossible = Action {
    val propertyNotConvertibe = (parsedString \ "product" \ "name").validate[Long].fold(
      errors => "Name is a String",
      s => s.toString)
    Ok(propertyNotConvertibe)
  }

  def validateRecursive = Action {
    val names = (rawJson \ "products" \\ "name").map(_.validate[String]) map {
      r => r fold (_ => "Error", s => s)
    }
    SimpleResult(
      header = ResponseHeader(200),
      body = Enumerator(names: _*))
  }

  //Convert a scala value to JsValue
  def scalaToJson = Action {
    val jsNumber: JsValue = Json.toJson(123)
    val jsListNumbers: JsValue = Json.toJson(Seq(1, 2, 3))
    val tupleConvert: JsValue = Json.toJson(Seq(Json.toJson(1), Json.toJson("jamal"), Json.toJson(1.23)))
    val mapConvert: JsValue = Json.toJson(Map("all" -> Seq(Json.toJson(Map("id" -> Json.toJson(1))))))

    val rrr = (JsPath \ "key1").read[String]
    Ok(Json.toJson(jsNumber :: jsListNumbers :: tupleConvert :: mapConvert :: Nil))
  }

  //Reads : Json To Scala Value with validation
  val jsContractor = Json.obj("name" -> JsString("jamal"),
    "email" -> JsString("jamal"),
    "garantie" -> JsString("4G"),
    "complement" -> JsString("complement"))
  def readContractor = Action {
    val contractor = Contractor.readContractor.reads(jsContractor)
    contractor fold (
      errors => {
        val rr = errors.foldLeft(" ")((acc, e) => acc + " " + "A ")
        Ok(errors.size.toString)
      },
      c => Ok(views.html.ch14.readContractor.render(c)))
  }

  val calcul = Json.obj("num_id" -> JsString("001231213889105"),
    "num_devis" -> JsString("rabelle_001234"),
    "mp_dt_nais" -> JsString("03/04/1974"),
    "mp_aa_adh" -> JsString("2010"),
    "mp_regime" -> JsString("1"),
    "mp_aphp" -> JsString("O"),
    "top_infoscj" -> JsString("O"),
    "top_infosenf" -> JsString("O"),
    "top_infosasc" -> JsString("O"),
    "cj_dt_nais" -> JsString("1975-10-18"),
    "cj_aa_adh" -> JsString("2010"),
    "cj_regime" -> JsString("1"),
    "cj_aphp" -> JsString("O"),
    "nb_enf_rg" -> JsString("2"),
    "nb_enf_am" -> JsString("2"),
    "nb_enf_ar" -> JsString("0"),
    "module_mp" -> JsString("0"),
    "module_cj" -> JsString("0"),
    "module_enf" -> JsString("0"),
    "module_asc1" -> JsString("0"),
    "module_asc2" -> JsString("0"),
    "module_asc3" -> JsString("0"),
    "module_asc4" -> JsString("0"),
    "asc1_dt_nais" -> JsString("1925-01-17"),
    "asc1_regime" -> JsString("2"),
    "asc2_dt_nais" -> JsString("1919-05-30"),
    "asc2_regime" -> JsString("2"),
    "asc3_dt_nais" -> JsString("1939-12-01"),
    "asc3_regime" -> JsString("1"),
    "asc4_dt_nais" -> JsString("1939-12-01"),
    "asc4_regime" -> JsString("1"),
    "garantie" -> JsString("4G"),
    "offre" -> JsString("01"),
    "aa_effet" -> JsString("102010"),
    "mp_prev" -> JsString("O"),
    "cj_prev" -> JsString("O"),
    "taux_mino" -> JsString("00"),
    "mp_anc" -> JsString("0"),
    "cj_anc" -> JsString("0"))

  def readCalcul = Action {
    
    val v = Json.prettyPrint(calcul)
    val r = CalculCotisations.readCalculCotisations.reads(calcul)
    r fold (
      errors => {
        errors.foreach(error => {
          error match {
            case (p, s) => {
              println("path : --> " + p)
              s.foreach(ve => println(ve))
            }
          }
        })

        Ok(errors.size.toString)
      },
      c => Ok(v))
  }
  
  def calculate = Action(parse.json) { implicit request => 
    val o = CalculCotisations.readCalculCotisations.reads(request.body)
    Ok(o.get.queryString)
  }
}