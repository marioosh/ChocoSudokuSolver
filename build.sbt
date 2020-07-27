import Dependencies._

ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.scalac"
ThisBuild / organizationName := "scalac"

lazy val root = (project in file("."))
  .settings(
    name := "ChocoSudokuSolver",
    libraryDependencies ++= Seq(choco, scalaTest)
  )

