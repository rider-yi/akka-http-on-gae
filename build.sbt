lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(
    scalaVersion := "2.12.4",
    libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.11",
    packageName in Docker := "akka-http-on-gae",
    dockerRepository := Some("asia.gcr.io"),
    dockerUsername := Some("your-gcp-project-name"),
    dockerBaseImage := "gcr.io/google-appengine/openjdk",
    javaOptions in Universal ++= Seq(
      "-J-Xms512m",
      "-J-Xmx1024m",
    )
  )
