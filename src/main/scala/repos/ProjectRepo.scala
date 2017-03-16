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

    def delete(empId: Int) = db.run{ projectTableQuery.filter(project => project.empId === empId).delete }

    def update(id: Int, name: String): Future[Int] = {
    val query = projectTableQuery.filter(_.empId === id)
    .map(_.name).update(name)

    db.run(query)
  }
}

object ProjectRepo extends ProjectRepo with DBComponentImpl
