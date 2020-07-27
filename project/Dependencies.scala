import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test
  lazy val choco = "org.choco-solver" % "choco-solver" % "4.10.2"
}
