import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val databinder_repo = "Databinder Repository" at "http://databinder.net/repo"
  val spde_sbt = "us.technically.spde" % "spde-sbt-plugin" % "0.4.2"

  val sonatype_repo = "Sonatype Repo" at "https://oss.sonatype.org/content/repositories/releases"
  val snuggletex_repo = "snuggletex_repo" at "http://www2.ph.ed.ac.uk/maven2"
  val posterous = "net.databinder" % "posterous-sbt" % "0.1.7"

  val android = "org.scala-tools.sbt" % "sbt-android-plugin" % "0.5.0"
}
