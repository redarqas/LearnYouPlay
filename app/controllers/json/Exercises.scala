package controllers.json

import scala.math.BigDecimal.double2bigDecimal
import scala.math.BigDecimal.int2bigDecimal

import models.Contractor
import models.Contractor.writeComplement
import play.api.libs.iteratee.Enumerator
import play.api.libs.json._
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.libs.json.Reads._
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.ResponseHeader
import play.api.mvc.SimpleResult

//Json Object : name / value
//value are : string, number, Json Object, Json array, true/false, null
//Equivalent types : JsString,  JsNumber, JsObject, JsArray, JsBoolean, JsNull
//Added Type : JsUndefined (Internal usage)
//Super Type : JsValue

object Application extends Controller {

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
    Ok(parsedString)
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
    Ok.chunked(Enumerator(productNames: _*))
  }

  //Option: Json safe conversion to scala value : using asOpt
  def convertToScalaVal = Action {
    val name: Option[String] = (parsedString \ "product" \ "name").asOpt[String]
    val nameNotFound: Option[String] = (parsedString \ "product" \ "propertyNotInJson").asOpt[String]
    val conversionNotPossible: Option[Long] = (parsedString \ "product" \ "propertyNotPresent").asOpt[Long]
    Ok(views.html.json.convertToScalaVal.render(name, nameNotFound, conversionNotPossible))
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
    Ok.chunked(Enumerator(names: _*))
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
    "podium" -> JsString("OR"),
    "complement" -> JsString("complement"))

  def readContractor = Action {
    val contractor = Contractor.readContractor.reads(jsContractor)
    contractor fold (
      errors => {
        val rr = errors.foldLeft(" ")((acc, e) => acc + " " + "A ")
        Ok(errors.size.toString)
      },
      c => Ok(views.html.json.readContractor.render(c)))
  }

  def calculate = Action { implicit request =>
    //QueryString to Json 
    val v = Json.toJson(request.queryString.map(r => r._1 -> r._2.head))
    Contractor.readContractor.reads(v) map { r =>
      Ok(Json.toJson(r))
    } recoverTotal {
      e => BadRequest("Detected error:" + JsError.toFlatJson(e))
    }
  }
}