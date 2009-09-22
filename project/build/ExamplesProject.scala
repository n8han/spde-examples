import sbt._
import spde._

class ExamplesProject(info: ProjectInfo) extends ParentProject(info)
{
  lazy val explode = project("Explode", "Explode", new SpdeProject(_))  
  lazy val flocking = project("Flocking", "Flocking", new SpdeProject(_))  
  lazy val fold = project("Fold", "Fold", new SpdeProject(_))  
  lazy val list = project("List", "List", new SpdeProject(_))
  lazy val gasket = project("Sierpinski", "Sierpinski_Gasket", new SpdeProject(_))
  lazy val matrix = project("Matrix", "Matrix", new SpdeOpenGLProject(_))

  /* Video projects use GSVideo, which is hard to set up on the Mac. */
  lazy val loop = project("Loop", "Loop", new SampleVideoProject(_))
  lazy val scratchP = project("Scratch", "Scratch", new SampleVideoProject(_))
}
