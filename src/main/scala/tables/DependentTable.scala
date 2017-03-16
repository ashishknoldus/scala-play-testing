package tables

import com.google.inject.Inject
import connections.DBComponent
import models.Dependent
/**
  * Created by knoldus on 15/3/17.
  */
class DependentTable @Inject()(dBComponent: DBComponent){

  import dBComponent.driver.api._


  //"experienced_employee" is the table name
  //Employee is case class <-> Can be replaced here with tuple
  private[models] class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent"){

    val name: Rep[String] = column[String]("name")
    val userName: Rep[String] = column[String]("userName")
    val email: Rep[String] = column[String]("email", O.PrimaryKey)
    val age: Rep[Int] = column[Int]("age")
    val gender: Rep[String] = column[String]("gender")
    val password: Rep[String] = column[String]("password")

    def * = (name, email, userName, age, gender, password) <>(Dependent.tupled, Dependent.unapply)
  }

  val dependentTableQuery:TableQuery[DependentTable] = TableQuery[DependentTable] //DependentTableQuery is used to create and execute queries on EmployeeTable

}

