import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
	val spde_sbt = "net.databinder.spde" % "spde-sbt-plugin" % "0.1.4-SNAPSHOT"
	val archetect = "net.databinder" % "archetect-plugin" % "0.1.2-SNAPSHOT"
}
