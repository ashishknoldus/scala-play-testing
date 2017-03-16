import connections.DBComponentImpl
import models.Employee
import repos.EmployeeRepo
import tables.EmployeeTable

/**
  * Created by knoldus on 15/3/17.
  */
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
EmployeeRepo.create
    val res = EmployeeRepo.insert(Employee(466434655,"sam","edrjhgdhshjgffg@ghj.com","tgyhu",3,"ytuh","pass") :: Nil)
    res.map(x => print(s"\n\n${x.toString}\n\n"))
      .recover{case throwable: Throwable => println(s"\n\n${throwable.getMessage} \n\n")}
    Thread.sleep(2000)

}
