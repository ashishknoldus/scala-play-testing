package connections
import slick.jdbc.JdbcProfile
/**
  * Created by knoldus on 15/3/17.
  */
trait DBComponent {

  val driver: JdbcProfile
  import driver.api._
  val db: Database

}
