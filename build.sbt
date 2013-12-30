name := "run"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings

templatesImport ++= Seq(
  "models.templating._",
  "models.forms._"
)