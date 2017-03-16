package repos

import com.google.inject.Inject
import connections.DBComponentImpl
import models.Employee
import tables.{EmployeeTable, ProjectTable}

import scala.concurrent.Future

/**
  * Created by knoldus on 14/3/17.
  */
class EmployeeRepo @Inject()( val dBComponent: DBComponentImpl,
                              employeeTable: EmployeeTable,
                              projectTable: ProjectTable
                            ) {

  import dBComponent.driver.api._

  def dropTable = dBComponent.db.run {
    employeeTable.employeeTableQuery.schema.drop
  }

  def create = dBComponent.db.run {
    employeeTable.employeeTableQuery.schema.create
  }

  def insert(empList: List[Employee]) = {

    val dbioList = empList.map(emp => {
      employeeTable.employeeTableQuery += emp
    })

    val action = DBIO.sequence(dbioList)

    dBComponent.db.run {
      action
    }

  }

  def insertOrUpdate(emp: Employee) = dBComponent.db.run {
    employeeTable.employeeTableQuery.insertOrUpdate(emp)
  }

  def delete(exp: Int) = dBComponent.db.run {
    employeeTable.employeeTableQuery.filter(employee => employee.age <= 18).delete
  }

  def getAll() = dBComponent.db.run {
    employeeTable.employeeTableQuery.to[List].result
  }

  def update(email: String, name: String): Future[Int] = {
    val query = employeeTable.employeeTableQuery.filter(_.email === email)
      .map(_.name).update(name)

    dBComponent.db.run(query)
  }

  def crossJoinEmpProject = {
    val query = for {
      (e, p) <- employeeTable.employeeTableQuery join projectTable.projectTableQuery

    } yield (e.name, p.name)

    dBComponent.db.run(query.to[List].result)
  }

  /*
  def crossJoinEmpProject = {
    val query = for {
      (e, p) <- employeeTable.employeeTableQuery join projectTable.projectTableQuery

    } yield (e.name, p.name)

    dBComponent.db.run(query)
  }

  def crossJoinEmpProject = {
    val query = for {
      (e, p) <- employeeTable.employeeTableQuery join projectTable.projectTableQuery

    } yield (e.name, p.name)

    dBComponent.db.run(query)
  }*/


}
