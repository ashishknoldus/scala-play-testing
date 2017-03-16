import connections.DBComponentImpl
import repos.EmployeeRepo
import tables.EmployeeTable

/**
  * Created by knoldus on 15/3/17.
  */
import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    val createTable = new EmployeeRepo(new EmployeeTable(new DBComponentImpl)).create
    val result  = createTable.map{ result => s"$result -- table created"}.recover{
      case ex: Throwable => ex.getMessage
    }

    result.map(println(_))

    Thread.sleep(2000)
  }
}
