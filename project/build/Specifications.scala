import sbt._

class Specifications(info: ProjectInfo) extends DefaultProject(info) {
  override def compileOptions = Deprecation :: Unchecked :: Nil
  val mavenLocal = "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"
  override def libraryDependencies = Set(
    
    "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test",
    "org.mockito" % "mockito-all" % "1.8.4" % "test",
    "junit" % "junit" % "4.7" % "test"
  )
}
