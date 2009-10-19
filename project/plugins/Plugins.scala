import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
	val spde_sbt = "net.databinder.spde" % "spde-sbt-plugin" % "0.2.0-SNAPSHOT"
}
