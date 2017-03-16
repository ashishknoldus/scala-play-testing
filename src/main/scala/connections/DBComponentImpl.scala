package connections

import com.google.inject.Inject
import com.typesafe.config.ConfigFactory
import slick.jdbc.JdbcProfile

/**
  * Created by knoldus on 13/3/17.
  */

@Inject
class DBComponentImpl extends DBComponent{

  val dbType: String = ConfigFactory.load().getString("db-type")

  val (driverInstance: JdbcProfile, configString: String) = if(dbType == "mysql") {

    (slick.jdbc.MySQLProfile, "mySqlDb")

  } else {

    (slick.jdbc.PostgresProfile, "postgresDB")

  }

  val driver = driverInstance
  import driver.api._
  val db: Database = Database.forConfig(configString)
}
