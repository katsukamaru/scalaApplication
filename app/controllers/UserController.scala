package controllers

import play.api.mvc._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

/**
  * Created by shin on 2018/03/02.
  */
object UserController extends Controller {
  def index = Action {
    val db = Database.forConfig("mysql")
    val query = sql"SELECT id, username FROM student".as[(Int, String)]
    val f:Future[Vector[(Int, String)]] = db.run(query)
    Ok("ok")
  }
}


