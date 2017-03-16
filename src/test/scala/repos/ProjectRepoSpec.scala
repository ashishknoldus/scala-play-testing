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

}
