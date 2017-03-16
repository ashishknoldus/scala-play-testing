package repos


import com.google.inject.Inject
import connections.DBComponentImpl
import models.Project
import tables.ProjectTable

import scala.concurrent.Future
/**
  * Created by knoldus on 15/3/17.
  */
class ProjectRepo @Inject()( val dBComponent: DBComponentImpl, projectTable: ProjectTable){

  import dBComponent.driver.api._

  //db is the object that is specified with the details of DB
    val db = Database.forConfig("myPostgresDB") //myPostgresDB is the name of configuration in application.conf

    def dropTable = db.run {projectTable.projectTableQuery.schema.drop}

    def create = db.run{ projectTable.projectTableQuery.schema.create } //db.run return the O/P wrapped in Future

    def insert(project: Project) = db.run{ projectTable.projectTableQuery += project }

    def delete(empId: Int) = db.run{ projectTable.projectTableQuery.filter(project => project.empId === empId).delete }

    def update(id: Int, name: String): Future[Int] = {
    val query = projectTable.projectTableQuery.filter(_.empId === id)
    .map(_.name).update(name)

    db.run(query)
  }
}
