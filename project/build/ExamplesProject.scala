import sbt._
import spde._

class ExamplesProject(info: ProjectInfo) extends ParentProject(info)
{
  lazy val explode = project("Explode", "Explode", new SpdeProject(_))  
  lazy val flocking = project("Flocking", "Flocking", new SpdeProject(_))  
  lazy val fold = project("Fold", "Fold", new SpdeProject(_))  
  lazy val list = project("List", "List", new SpdeProject(_))
  lazy val matrix = project("Matrix", "Matrix", new SpdeOpenGLProject(_))
}