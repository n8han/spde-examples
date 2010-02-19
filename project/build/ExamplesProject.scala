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


  lazy val trending = project("Trending", "Trending", new DefaultSpdeProject(_) {
    val dispatch = "net.databinder" %% "dispatch-lift-json" % "0.7.0"
  })
  lazy val straight_scala = project("Straight_Scala", "Straight_Scala", new DefaultSpdeProject(_))
  lazy val straight_java = project("Straight_Java", "Straight_Java", new DefaultSpdeProject(_))
}
