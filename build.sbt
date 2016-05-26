name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  "org.webjars.bower" % "adminlte" % "2.3.3",
  "org.webjars" % "react" % "15.0.1",
  "org.webjars" % "react-router" % "1.0.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

fork in run := false