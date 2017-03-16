package connections

import java.util.UUID

import slick.jdbc.JdbcProfile

/**
  * Created by knoldus on 15/3/17.
  */
trait H2DBComponent extends DBComponent{

  override val driver = slick.jdbc.H2Profile

  import driver.api._

  private val randomDb = "jdbc:h2:mem:test" + UUID.randomUUID().toString + ";"
  private val h2Url = randomDb + "MODE=MySql;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'src/test/resources/schema.sql'\\;RUNSCRIPT FROM 'src/test/resources/initialdata.sql'"
  println(s"\n\n~~~~~~~~~~~~~~~~~~~~~             $h2Url         ~~~~~~~~~~~~~~~~~~~~~~~\n\n")

   val db: driver.api.Database = Database.forURL(url = h2Url, driver = "org.h2.Driver")
}
