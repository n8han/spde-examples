import sbt._
import spde._

class ExamplesProject(info: ProjectInfo) extends ParentProject(info) with posterous.Publish
{
  lazy val explode = project("Explode", "Explode", new DefaultSpdeProject(_))
  lazy val flocking = project("Flocking", "Flocking", new DefaultSpdeProject(_))
  lazy val fold = project("Fold", "Fold", new DefaultSpdeProject(_))
  lazy val continue = project("Continue", "Continue", new DefaultSpdeProject(_) with AutoCompilerPlugins {
    val continuations = compilerPlugin("org.scala-lang.plugins" % "continuations" % "2.8.0.RC7")
    override def compileOptions = CompileOption("-P:continuations:enable") :: super.compileOptions.toList
  })
  lazy val list = project("List", "List", new DefaultSpdeProject(_))
  lazy val gasket = project("Sierpinski", "Sierpinski_Gasket", new DefaultSpdeProject(_))
  lazy val lsystems = project("L-Systems", "L_Systems", new DefaultSpdeProject(_))
  lazy val matrix = project("Matrix", "Matrix", new DefaultOpenGLProject(_))
  lazy val planerotate = project("PlaneRotate", "PlaneRotate", new DefaultSpdeProject(_))
  lazy val ti_81 = project("TI-81", "TI-81", new DefaultSpdeProject(_))
  lazy val geometry = project("Geometry", "Geometry", new DefaultOpenGLProject(_))
  lazy val esfera = project("Esfera", "Esfera", new DefaultOpenGLProject(_))
  lazy val fractalParticles = project("FractalParticles", "FractalParticles", new DefaultOpenGLProject(_))
  
  /* Video projects use GSVideo, see VIDEO.md for more info. */
  lazy val loop = project("Loop", "Loop", new SampleVideoProject(_))
  lazy val scratchP = project("Scratch", "Scratch", new SampleVideoProject(_))


  lazy val trending = project("Trending", "Trending", new DefaultSpdeProject(_) {
    val dispatch = "net.databinder" %% "dispatch-lift-json" % "0.7.4"
  })
  lazy val straight_scala = project("Straight_Scala", "Straight_Scala", new DefaultSpdeProject(_) {
    override def sketchClass = "StraightScala"
  })
  lazy val android = project("Android", "Android", new AndroidProject(_) with SpdeAndroidProject {
    def androidPlatformName="android-2.0"
    override def sketchClass = "Fold"
  })
  lazy val straight_java = project("Straight_Java", "Straight_Java", new DefaultSpdeProject(_) {
    override def sketchClass = "StraightJava"
    // set the path below to your rt.jar / classes.jar, if you want applet export to work.
    // We can't find it automatically for plain Java projects.
    override def proguardOptions =
      "-libraryjars \"/System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/Classes/classes.jar\"" :: super.proguardOptions
  })
}
