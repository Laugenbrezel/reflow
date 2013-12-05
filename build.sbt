name := "reflow"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  // WebJars pull in client-side web libraries
  //"org.webjars" %% "webjars-play" % "2.2.1",
  "org.webjars" %% "webjars-play" % "2.2.1" exclude("org.scala-lang", "scala-library"),
  "org.webjars" % "bootstrap" % "3.0.2",
  //"org.webjars" % "bootstrap" % "2.3.1"  
  "org.webjars" % "jquery" % "1.9.0"  
)     

play.Project.playJavaSettings
