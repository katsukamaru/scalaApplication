package controllers

import play.api.mvc._

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Application extends Controller {

  var db = Database.forConfig("mysql")
  def index = Action {
    // val db = Database.forURL("jdbc:mysql://localhost/mydb", driver="com.mysql.jdbc.Driver", user="root", password="")
    val query = sql"SELECT id, username FROM student".as[(Int, String)]
    val f:Future[Vector[(Int, String)]] = db.run(query)
    Await.result(f, Duration.Inf) foreach println

    Ok(views.html.index("Your new application is ready."))
  }

}