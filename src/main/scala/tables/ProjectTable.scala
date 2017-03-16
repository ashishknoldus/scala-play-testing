package tables

import connections.{DBComponent, DBComponentImpl}
import models.Project
/**
  * Created by knoldus on 15/3/17.
  */
trait ProjectTable extends EmployeeTable{

  this: DBComponent =>
  import driver.api._

  class ProjectTable(tag: Tag) extends Table[Project](tag, "project"){

    val empId: Rep[Int] = column[Int]("empId", O.PrimaryKey, O.AutoInc)
    val name: Rep[String] = column[String]("name")

    def employeeProjectFK = foreignKey(
      "employee_project_FK", empId, employeeTableQuery
    )(_.id)
    def * = (empId, name) <>(Project.tupled, Project.unapply)
  }

  val projectTableQuery:TableQuery[ProjectTable] = TableQuery[ProjectTable] //employeeTableQuery is used to create and execute queries on EmployeeTable
}

