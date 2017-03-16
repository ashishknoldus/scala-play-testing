package repos

import connections.{DBComponent, DBComponentImpl}
import models.Employee
import tables.{EmployeeTable, ProjectTable}

import scala.concurrent.Future

/**
  * Created by knoldus on 14/3/17.
  */
trait EmployeeRepo extends EmployeeTable with ProjectTable {

  this: DBComponent =>

  import driver.api._

  def dropTable = db.run {
    employeeTableQuery.schema.drop
  }

  def create = db.run {
    employeeTableQuery.schema.create
  }

  def insert(empList: List[Employee]) = {

    val dbioList = empList.map(emp => {
      employeeTableQuery += emp
    })

    val action = DBIO.sequence(dbioList)

    db.run {
      action
    }

  }

  def insertOrUpdate(emp: Employee) = db.run {
    employeeTableQuery.insertOrUpdate(emp)
  }

  def delete(exp: Int) = db.run {
    employeeTableQuery.filter(employee => employee.age <= 18).delete
  }

  def getAll() = db.run {
    employeeTableQuery.to[List].result
  }

  def update(email: String, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(_.email === email)
      .map(_.name).update(name)

    db.run(query)
  }

  def crossJoinEmpProject = {
    val query = for {
      (e, p) <- employeeTableQuery join projectTableQuery

    } yield (e.name, p.name)

    db.run(query.to[List].result)
  }

  /*
  def crossJoinEmpProject = {
    val query = for {
      (e, p) <- employeeTableQuery join projectTable.projectTableQuery

    } yield (e.name, p.name)

    db.run(query)
  }

  def crossJoinEmpProject = {
    val query = for {
      (e, p) <- employeeTableQuery join projectTable.projectTableQuery

    } yield (e.name, p.name)

    db.run(query)
  }*/


}

object EmployeeRepo extends EmployeeRepo with DBComponentImpl
