package controllers

import play.api.mvc._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration


object Application extends Controller {
  val db = Database.forConfig("mysql")

  def index = Action {
    val query = sql"SELECT id, username FROM student".as[(Int, String)]
    val f:Future[Vector[(Int, String)]] = db.run(query)
    Await.result(f, Duration.Inf) foreach println

    Ok(views.html.index("Your new application is ready."))
  }

  def saka = Action {
    val query = sql"SELECT id, username FROM student WHERE username = 'sakamoto'".as[(Int, String)]
    val f:Future[Vector[(Int, String)]] = db.run(query)
    Await.result(f, Duration.Inf) foreach println
    Ok(views.html.index("Your new application is ready."))
  }

}