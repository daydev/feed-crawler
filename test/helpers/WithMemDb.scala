package helpers

import helpers.WithMemDb._
import play.api.GlobalSettings
import play.api.test.Helpers._
import play.api.test._

class WithMemDb(
                 datasourceName: String = "test",
                 mode: Option[H2Mode] = None,
                 global: Option[GlobalSettings] = Some(new GlobalSettings() {}),
                 additionalConfig: Map[String, String] = Map.empty)
  extends WithApplication(
    FakeApplication(
      additionalConfiguration = inMemoryDatabase(
        name = datasourceName,
        options = mode.map(mode => Map("MODE" -> mode.name)).getOrElse(Map.empty)
      ) ++ additionalConfig,
      withGlobal = global
    )) {

}

object WithMemDb {

  abstract class H2Mode(val name: String)

  case object PostreSql extends H2Mode("PostgreSQL")

  case object MySql extends H2Mode("MYSQL")

  case object DB2 extends H2Mode("DB2")

  case object Derby extends H2Mode("DERBY")

  case object HSQLDB extends H2Mode("HSQLDB")

  case object MSSQL extends H2Mode("MSSQLServer")

  case object Oracle extends H2Mode("Oracle")

}