package controllers.database

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
/*
 * import play.api.Play.current
 * import play.api.libs._
 * import play.api.libs.iteratee._
 * import play.api.libs.concurrent._
 * import java.util.concurrent._
 * import scala.concurrent.stm._
 * import akka.util.duration._
 * import play.api.cache._
 * import play.api.libs.json._
 */

object Application extends Controller {

  def example() = Action {
    DB.withConnection { conn =>
      var out = "Number is : "
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("select 9 as key")
      while (rs.next()) {
        out += rs.getString("key")
      }
      Ok(out)
    }
  }
  //Anorm example
  def anorm() = Action {
    DB.withConnection { implicit conn =>
      val result = SQL("select 1").execute()
      Ok(result.toString)
    }
  }
  //anorm delete
  def delete = Action {
    DB.withTransaction { implicit conn =>
      val result: Int = SQL("delete from city where id = 99").executeUpdate()
      Ok(result.toString)
    }
  }
  //anorm insert
  def insert = Action {
    DB.withTransaction { implicit conn =>
      val id: Option[Long] = SQL("insert into city(name, country) values ({name}, {country})")
        .on('name -> "Paris", 'country -> "France").executeInsert()
      Ok(id.toString)
    }
  }
  //anorm retrieve data
  def cities = Action {
    DB.withTransaction { implicit conn =>
      val query = SQL("select * from city")
      val cities: List[(String, String)] = query().map { row =>
        row[String]("name") -> row[String]("country")
      }.toList
      Ok(cities.toString)
    }
  }
  //anorm retrieve date with pattern matching
  case class FrenchCity(name: String)
  case class ForeignCity(name: String)

  def city = Action {
    DB.withTransaction { implicit conn =>
      val query = SQL("select name,country from city")
      val city = query().collect {
        case a @ Row(Some(name: String), Some("France")) =>
          Logger.info(a.toString()); FrenchCity(name)
        case a @ Row(Some(name: String), _) => Logger.info(a.toString()); ForeignCity(name)
      }.toList
      Ok(city.toString())
    }
  }
  //Parser API
  def count = Action {
    DB.withTransaction { implicit conn =>
      val query = SQL("select count(*) from city")
      val count: Long = query.as(scalar[Long].single)
      Ok(count.toString)
    }
  }

  def read = Action {
    DB.withTransaction { implicit conn =>
      val cities: List[(Long, String, String)] = SQL("select * from city").as((long("id") ~ str("name") ~ str("country")).map {
        case id ~ name ~ country => (id, name, country)
      } *)
      Ok(cities.toString)
    }
  }

  case class SpokenLangs(city: String, lang: Seq[String])

  def complex = Action {
    DB.withTransaction { implicit conn =>
      //parser
      val p: ResultSetParser[List[(String, String)]] = {
        (str("name") ~ str("lang")).map(flatten) *
      }
      val query = SQL("select c.name, l.lang from city c, language l where c.id = l.cityid")
      val citylangs: List[(String, String)] = query.as(p)
      val groups: Option[SpokenLangs] = citylangs.headOption.map { city =>
        SpokenLangs(city._1, citylangs.map(_._2))
      }
      Ok(groups.get.toString)
    }
  }

}