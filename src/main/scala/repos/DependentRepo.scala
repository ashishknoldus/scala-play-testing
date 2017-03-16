package repos

import connections.{DBComponent, DBComponentImpl}
import models.Dependent
import tables.DependentTable

import scala.concurrent.Future

/**
  * Created by knoldus on 15/3/17.
  */
trait DependentRepo extends DependentTable {

  this: DBComponent =>

  import driver.api._


  def dropTable = db.run {
    dependentTableQuery.schema.drop
  }

  def create = db.run {
    dependentTableQuery.schema.create
  } //db.run return the O/P wrapped in Future

  def insert(dependent: Dependent) = db.run {
    dependentTableQuery += dependent
  }

  def delete(exp: Int) = db.run {
    dependentTableQuery.filter(dependent => dependent.age <= 18).delete
  }

  def update(email: String, name: String): Future[Int] = {
    val query = dependentTableQuery.filter(_.email === email)
      .map(_.name).update(name)

    db.run(query)
  }
}

object DependentRepo extends DependentRepo with DBComponentImpl
