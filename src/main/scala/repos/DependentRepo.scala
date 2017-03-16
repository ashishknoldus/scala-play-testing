package repos

import com.google.inject.Inject
import connections.DBComponent
import models.Dependent
import tables.DependentTable

import scala.concurrent.Future

/**
  * Created by knoldus on 15/3/17.
  */
class DependentRepo @Inject()(val dBComponent: DBComponent, dependentTable: DependentTable) {

  import dBComponent.driver.api._


  def dropTable = db.run {dependentTable.dependentTableQuery.schema.drop}

  def create = db.run{ dependentTable.dependentTableQuery.schema.create } //db.run return the O/P wrapped in Future

  def insert(dependent: Dependent) = db.run{ dependentTable.dependentTableQuery += dependent }

  def delete(exp: Int) = db.run{ dependentTable.dependentTableQuery.filter(dependent => dependent.age <= 18).delete }

  def update(email: String, name: String): Future[Int] = {
    val query = dependentTable.dependentTableQuery.filter(_.email === email)
      .map(_.name).update(name)

    db.run(query)
  }
}
