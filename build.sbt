lazy val root = project
  .in(file("."))
  .settings(
    name := "openai-400-error",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "3.6.4",
    libraryDependencies += "com.openai" % "openai-java" % "0.34.1"
  )
