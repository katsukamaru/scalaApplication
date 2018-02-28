name := "scalaApplication"

version := "1.0"

lazy val `scalaapplication` = (project in file(".")).enablePlugins(PlayScala)


scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "com.typesafe.slick" %% "slick-codegen" % "3.2.1",
  "mysql" % "mysql-connector-java" % "5.1.34"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
