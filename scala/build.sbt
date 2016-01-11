lazy val root = (project in file(".")).
  settings(
    name := "atm-api",
    version := "0.0.1",
    scalaVersion := "2.11.7",
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
  )