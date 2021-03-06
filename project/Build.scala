import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "jam"
  val buildVersion      = "1.0"
  val buildScalaVersion = "2.10.3"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}


object Resolvers {
  val sunrepo    = "Sun Maven2 Repo" at "http://download.java.net/maven/2"
  val sunrepoGF  = "Sun GF Maven2 Repo" at "http://download.java.net/maven/glassfish"
  val oraclerepo = "Oracle Maven2 Repo" at "http://download.oracle.com/maven"

  val oracleResolvers = Seq (sunrepo, sunrepoGF, oraclerepo)
}

object Dependencies {
 val scalatest = "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
 val postgresql = "org.postgresql" % "postgresql" % "9.3-1100-jdbc41"
 val async = "org.scala-lang.modules" %% "scala-async" % "0.9.0-M4"
}

object AppBuild extends Build {
  import Resolvers._
  import Dependencies._
  import BuildSettings._

  // Sub-project specific dependencies
  val commonDeps = Seq (
    scalatest,
    postgresql,
    async
  )
  
  lazy val app = Project (
    "learnplay",
    file ("."),
    settings = buildSettings ++ Seq (libraryDependencies ++= commonDeps)
  )
}