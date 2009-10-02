import sbt._
import spde._

class ExamplesProject(info: ProjectInfo) extends ParentProject(info)
{
  lazy val explode = project("Explode", "Explode", new SpdeProject(_) with AppletProject)
  lazy val flocking = project("Flocking", "Flocking", new SpdeProject(_) with AppletProject)
  lazy val fold = project("Fold", "Fold", new SpdeProject(_))  
  lazy val list = project("List", "List", new SpdeProject(_))
  lazy val gasket = project("Sierpinski", "Sierpinski_Gasket", new SpdeProject(_))
  lazy val lsystems = project("L-Systems", "L_Systems", new SpdeProject(_))
  lazy val matrix = project("Matrix", "Matrix", new SpdeOpenGLProject(_))

  /* Video projects use GSVideo, see VIDEO.md for more info. */
  lazy val loop = project("Loop", "Loop", new SampleVideoProject(_))
  lazy val scratchP = project("Scratch", "Scratch", new SampleVideoProject(_))
}

trait AppletProject extends SpdeProject
{
  import java.io.File
  val proguardConfigurationPath: Path = outputPath / "proguard.pro"
  lazy val outputJar: Path = outputPath / (name + "-applet-" + version + ".jar")
  def rootProjectDirectory = rootProject.info.projectPath
  
  /****** Dependencies  *******/
  val defaultConfig = config("default")
  val toolsConfig = config("tools")
  val proguardJar = "net.sf.proguard" % "proguard" % "4.3" % "tools->default"
  
  /******** Proguard *******/
  lazy val proguard = proguardTask dependsOn(`package`, writeProguardConfiguration)
  lazy val writeProguardConfiguration = writeProguardConfigurationTask dependsOn `package`
  
  private def proguardTask =
    task
    {
      FileUtilities.clean(outputJar :: Nil, log)
      val proguardClasspath = managedClasspath(toolsConfig)
      val proguardClasspathString = Path.makeString(proguardClasspath.get)
      val configFile = proguardConfigurationPath.asFile.getAbsolutePath
      val exitValue = Process("java", List("-Xmx128M", "-cp", proguardClasspathString, "proguard.ProGuard", "@" + configFile)) ! log
      if(exitValue == 0) None else Some("Proguard failed with nonzero exit code (" + exitValue + ")")
    }
  def renderInfo = {
    val sz = """\s*size\s*\(\s*(\d+)\s*,\s*(\d+),?\s*(\S*)\s*\)\s*""".r
    val sz_line = io.Source.fromFile(sourceGlob.asFile).getLines.find(None != sz.findFirstIn(_))
    val renderers = Map (
      "P3D" -> "processing.core.PGraphics3D",
      "JAVA2D" -> "processing.core.PGraphicsJava2D",
      "OPENGL"-> "processing.opengl.PGraphicsOpenGL",
      "PDF"-> "processing.pdf.PGraphicsPDF",
      "DXF"-> "processing.dxf.RawDXF"
    ) withDefaultValue ("processing.core.PGraphicsJava2D")
    
    sz_line match {
      case Some(sz(width, height, r)) => (width, height, renderers(r))
      case Some(sz(width, height)) => (width, height, renderers(""))
      case _ => (100, 100, renderers(""))
    }
  }
  private def writeProguardConfigurationTask =
    task
    {
      // the template for the proguard configuration file
      val outTemplate = """
        |-dontobfuscate
        |-dontnote
        |-dontwarn
        |-libraryjars %s
        |%s
        |-outjars %s
        |-ignorewarnings
        |-keep class com.sun.opengl.impl.** { *** *(***); }
        |-keep class %s
        |-keep class MyRunner { *** main(...); }
        |-keep class processing.core.PApplet { *** main(...); }
        |-keep class spde.core.SApplet { *** scripty(...); }
        |-keepclasseswithmembers class * { public void dispose(); }
        |-keep public class org.apache.commons.logging.impl.LogFactoryImpl
        |-keep public class org.apache.commons.logging.impl.Jdk14Logger { *** <init>(...); }
        |-keep public class net.databinder.dispatch.Http* { scala.Function1 ok(); }
        |"""
      
      val defaultJar = (outputPath / defaultJarName).asFile.getAbsolutePath
      log.debug("proguard configuration using main jar " + defaultJar)
      val externalDependencies = (mainCompileConditional.analysis.allExternals).map(_.getAbsoluteFile).filter(_.getName.endsWith(".jar"))
      log.debug("proguard configuration external dependencies: \n\t" + externalDependencies.mkString("\n\t"))
      // partition jars from the external jar dependencies of this project by whether they are located in the project directory
      // if they are, they are specified with -injars, otherwise they are specified with -libraryjars
      val (externalJars, libraryJars) = externalDependencies.toList.partition(jar => Path.relativize(rootProjectDirectory, jar).isDefined)
      log.debug("proguard configuration library jars locations: " + libraryJars.mkString(", "))
      // exclude properties files and manifests from scala-library jar
      val inJars = (defaultJar :: externalJars.map( _ + "(!META-INF/**,!*.txt)")).map("-injars " + _).mkString("\n")
      
      val (width, height, renderer) = renderInfo
      
      val proguardConfiguration =
        outTemplate.stripMargin.format(libraryJars.mkString(File.pathSeparator),
          inJars, outputJar.absolutePath, name) + (
            "-keep class %s { *** <init>(...); }\n" format renderer
          )
      log.debug("Proguard configuration written to " + proguardConfigurationPath)
      FileUtilities.write(proguardConfigurationPath.asFile, proguardConfiguration, log)
    }
}
