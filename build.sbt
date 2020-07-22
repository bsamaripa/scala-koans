import Dependencies._

addCommandAlias("namaste", "~testOnly org.scalakoans.Koans")

lazy val root = (project in file("."))
  .settings(
    name := "Scala Koans Neo",
    scalaVersion := "2.13.3",
    version      := "2.2",
    organization := "org.scalakoans",
    libraryDependencies ++= backendDeps,
    scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature"),
    traceLevel in test := -1,
    parallelExecution in test := false,
    logLevel := Level.Info,
    showTiming := false,
    showSuccess := false
  )
