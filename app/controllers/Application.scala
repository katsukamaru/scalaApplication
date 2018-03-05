package controllers

import play.api.mvc._
import scalikejdbc._

object Application extends Controller {

  def index = Action {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost:32769/test", "sa", "")
    DB readOnly { implicit session =>
      val entities: List[Map[String, Any]] = sql"select * from tutorials_tbl".map(_.toMap).list.apply()
      entities.foreach(println(_))
    }
    Ok(views.html.index("Your new application is ready."))
  }

  def echo = Action {
    Redirect("/user")
  }

  def notImple() = TODO

}


