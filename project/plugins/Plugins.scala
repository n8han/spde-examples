import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val databinder_repo = "Databinder Repository" at "http://databinder.net/repo"
	val spde_sbt = "us.technically.spde" % "spde-sbt-plugin" % "0.2.3"
}
