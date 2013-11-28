name := "reflow"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  // WebJars pull in client-side web libraries
  "org.webjars" %% "webjars-play" % "2.2.1",
  "org.webjars" % "bootstrap" % "3.0.2"
  //"org.webjars" % "bootstrap" % "2.3.1"  
)     

play.Project.playJavaSettings
