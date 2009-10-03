import sbt._
import spde._

class ExamplesProject(info: ProjectInfo) extends ParentProject(info)
{
  lazy val explode = project("Explode", "Explode", new DefaultSpdeProject(_))
  lazy val flocking = project("Flocking", "Flocking", new DefaultSpdeProject(_))
  lazy val fold = project("Fold", "Fold", new DefaultSpdeProject(_))
  lazy val list = project("List", "List", new DefaultSpdeProject(_))
  lazy val gasket = project("Sierpinski", "Sierpinski_Gasket", new DefaultSpdeProject(_))
  lazy val lsystems = project("L-Systems", "L_Systems", new DefaultSpdeProject(_))
  lazy val matrix = project("Matrix", "Matrix", new DefaultOpenGLProject(_))

  /* Video projects use GSVideo, see VIDEO.md for more info. */
  lazy val loop = project("Loop", "Loop", new SampleVideoProject(_))
  lazy val scratchP = project("Scratch", "Scratch", new SampleVideoProject(_))
}