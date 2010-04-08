import sbt._
import spde._

class ExamplesProject(info: ProjectInfo) extends ParentProject(info) with posterous.Publish
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
  lazy val straight_scala = project("Straight_Scala", "Straight_Scala", new DefaultSpdeProject(_) {
    override def sketchClass = "StraightScala"
  })
  lazy val android = project("Android", "Android", new AndroidProject(_) with SpdeProject {
    val core_android = "org.processing" % "core-android" % "0183" from "http://dev.processing.org/source/index.cgi/*checkout*/tags/processing-0183/android/core.zip?rev=6573"
    def androidPlatformName="android-2.0"
    override def sketchClass = "StraightScala"
  })
  lazy val straight_java = project("Straight_Java", "Straight_Java", new DefaultSpdeProject(_) {
    override def sketchClass = "StraightJava"
    // set the path below to your rt.jar / classes.jar, if you want applet export to work.
    // We can't find it automatically for plain Java projects.
    override def proguardOptions =
      "-libraryjars \"/System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/Classes/classes.jar\"" :: super.proguardOptions
  })
}
