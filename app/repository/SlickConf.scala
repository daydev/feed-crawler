package repository

import com.github.tototoshi.slick.GenericJodaSupport
import play.api.Play.current
import play.api.db.slick.Database

import scala.slick.driver.JdbcDriver

object SlickConf {
  def db(name: String): Database = Database(name)

  lazy val Driver: JdbcDriver = play.api.db.slick.Config.driver

  lazy val JodaSupport = new GenericJodaSupport(Driver)
}