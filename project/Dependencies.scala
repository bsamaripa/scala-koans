import sbt._

object Dependencies {
  lazy val scalaTest = "3.0.7"

  lazy val backendDeps = Seq(
    "org.scalatest" %% "scalatest"   % scalaTest % "test"
  )
}
