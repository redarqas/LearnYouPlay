import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "run"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "guillaume.bort" %% "cagette" % "0.3-SNAPSHOT",
    "org.specs2" %% "specs2" % "1.14" % "test"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
