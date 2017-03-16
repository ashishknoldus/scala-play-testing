package connections
import java.util.UUID

import slick.jdbc.JdbcProfile

/**
  * Created by knoldus on 15/3/17.
  */
class H2DBComponent extends DBComponent{
  val driver: JdbcProfile = slick.jdbc.H2Profile
  import driver.api._

  val randomDb = "jdbc:h2:mem:test" + UUID.randomUUID().toString + ";"
  val h2Url = randomDb + "MODE=MySql;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'src/test/resources/schema.sql'\\\\;RUNSCRIPT FROM 'src/test/resources/initaldata.sql"
  println(s"\n\n~~~~~~~~~~~~~~~~~~~~~             $h2Url         ~~~~~~~~~~~~~~~~~~~~~~~\n\n")

  val db: driver.api.Database = Database.forURL(url = h2Url, driver = "org.h2.Driver")
}
