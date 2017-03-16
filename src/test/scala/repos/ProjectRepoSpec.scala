package repos

import connections.H2DBComponent
import models.Project
import org.scalatest.AsyncFunSuite

/**
  * Created by knoldus on 16/3  /17.
  */
class ProjectRepoSpec extends AsyncFunSuite with ProjectRepo with H2DBComponent {


  test("it should add new project") {
    insert(Project(23, "Dummy Project")).map(x => assert(x == 1))
  }

  test("it should delete a project") {
    delete(23).map(x => assert(x == 1))
  }

  test("it should update a project") {
    update(23, "Real Project").map(x => assert(x == 1))
  }

  test("it should insert or update a project") {
    insertOrUpdate(Project(21, "Second real project")).map(x => assert(x == 1))
  }

  test("it should get all the rows in table") {
    getAll.map(x => assert(x == List(Project(21,"Java 8 Crawler analysis"), Project(23,"Play Scala Website"))))
  }

  test("it should join the tables") {

    crossJoinProjectEmp.map(x => assert(x == List(("Ashish","Java 8 Crawler analysis"),
    ("Simar KAUR","Java 8 Crawler analysis"),
    ("Ashish","Play Scala Website"),
    ("Simar KAUR","Play Scala Website")))
    )

  }

  test("it should get the name of project based on empId -- the method uses the plain SQL query") {

    insertWithSql(23).map(x => assert(x == 1))
  }



}
