name := """my-bookshelf"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

// scalikeJDBC
libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"                       % "2.2.+",
  "org.scalikejdbc" %% "scalikejdbc-config"                % "2.2.+",
  "org.scalikejdbc" %% "scalikejdbc-play-plugin"           % "2.3.+",
  "org.scalikejdbc" %% "scalikejdbc-play-fixture-plugin"   % "2.3.+", // optional
  "com.h2database"  %  "h2"                % "1.4.186",
  "ch.qos.logback"  %  "logback-classic"   % "1.1.2"
)

libraryDependencies ++= Seq(
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

libraryDependencies ++= Seq(
  "com.github.tototoshi" %% "play-flyway" % "1.2.1"
)

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "org.slf4j" % "slf4j-api" % "1.7.10",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

// mongodb
libraryDependencies ++= Seq(
  "se.radley" %% "play-plugins-salat" % "1.5.0"
)

// This may not be needed in development, also you cannot view in-memory h2 database with using h2-browser if process is forked.
//fork in run := true
