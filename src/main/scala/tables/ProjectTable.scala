package tables



import com.google.inject.Inject
import connections.DBComponent
import models.Project
/**
  * Created by knoldus on 15/3/17.
  */
class ProjectTable @Inject()(val dBComponent: DBComponent, employeeTable: EmployeeTable){

  import dBComponent.driver.api._

  private[example] class ProjectTable(tag: Tag) extends Table[Project](tag, "project"){

    val empId: Rep[Int] = column[Int]("id", O.PrimaryKey)
    val name: Rep[String] = column[String]("name")

    def employeeProjectFK = foreignKey(
      "employee_project_FK", empId, employeeTable.employeeTableQuery
    )(_.id)
    def * = (empId, name) <>(Project.tupled, Project.unapply)
  }


  val projectTableQuery:TableQuery[ProjectTable] = TableQuery[ProjectTable] //employeeTableQuery is used to create and execute queries on EmployeeTable

}
