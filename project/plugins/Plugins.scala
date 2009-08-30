import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
	val extract = "us.technically" % "spde-sbt-plugin" % "0.1-SNAPSHOT"
}