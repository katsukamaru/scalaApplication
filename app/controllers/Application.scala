package controllers

import play.api.mvc._
import slick.jdbc.MySQLProfile.api._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration


object Application extends Controller {

  def index = Action {
    val db = Database.forConfig("mysql")
    val query = sql"SELECT id, username FROM student".as[(Int, String)]
    val f:Future[Vector[(Int, String)]] = db.run(query)
    Await.result(f, Duration.Inf) foreach println

    Ok(views.html.index("Your new application is ready."))
  }

  def saka = Action {
    val db = Database.forConfig("mysql")
    val query = sql"SELECT id, username FROM student WHERE username = 'sakamoto'".as[(Int, String)]
    val f:Future[Vector[(Int, String)]] = db.run(query)
    Await.result(f, Duration.Inf) foreach println
    Ok(views.html.index("Your new application is ready."))
  }

  def echo = Action {
    Redirect("/user")
  }

  def notImple() = TODO

  def listPlaces = Action {
    val json = Json.toJson(Place.list)
    Ok(json)
  }

  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and (JsPath \ "long").write[Double]) (unlift(Location.unapply))

  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "location").write[Location]
    )(unlift(Place.unapply))

  case class Location(lat: Double, long: Double)

  case class Place(name: String, location: Location)

  object Place {

    var list: List[Place] = {
      List(
        Place(
          "Sandleford",
          Location(51.377797, -1.318965)
        ),
        Place(
          "Watership Down",
          Location(51.235685, -1.309197)
        )
      )
    }

    def save(place: Place) = {
      list = list ::: List(place)
    }
  }


}


