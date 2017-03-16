package repos

import connections.{DBComponent, DBComponentImpl}
import models.Project
import tables.ProjectTable

import scala.concurrent.Future
/**
  * Created by knoldus on 15/3/17.
  */
trait ProjectRepo extends ProjectTable{

  this: DBComponent =>
  import driver.api._

  def dropTable = db.run {projectTableQuery.schema.drop}

  def create = db.run{ projectTableQuery.schema.create } //db.run return the O/P wrapped in Future

  def insert(project: Project) = db.run{ projectTableQuery += project }

  //Use the below line to return the AutoInc field of inserted row i.e. empId - Arpit's assignment
  //But it does not work with testing
  //It throws exception
  //def insert(project: Project) = db.run{ projectTableQuery returning projectTableQuery.map(_.empId) += project }

  def delete(empId: Int) = db.run{ projectTableQuery.filter(project => project.empId === empId).delete }

  def update(id: Int, name: String): Future[Int] = {
    val query = projectTableQuery.filter(_.empId === id)
    .map(_.name).update(name)

    db.run(query)
  }

  def insertOrUpdate(project: Project) = {
    db.run {
      projectTableQuery.insertOrUpdate(project)
    }
  }

  def getAll: Future[List[Project]] = db.run {
    projectTableQuery.to[List].result
  }

  def crossJoinProjectEmp = {
    val query = for {
      (p, e) <- projectTableQuery join employeeTableQuery

    } yield (e.name, p.name)

    db.run(query.to[List].result)
  }

  def insertWithSql(empId: Int) = {

    val action: DBIO[Int] = sqlu"delete from project where empId = ${empId}"
    db.run(action)

  }
}

object ProjectRepo extends ProjectRepo with DBComponentImpl
