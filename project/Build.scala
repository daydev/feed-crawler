import play.Play.autoImport._
import sbt.Keys._
import sbt._

object ApplicationBuild extends Build {

  val appName = "feed-crawler"
  val appVersion = "1.0-SNAPSHOT"

  val akkaVersion = "2.3.7"
  val slickVersion = "2.1.0"
  val playSlickVersion = "0.8.0"

  val akka = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val jodaTime = "joda-time" % "joda-time" % "2.4"
  val jodaTimeConvert = "org.joda" % "joda-convert" % "1.6"
  val jodaTimeMapper = "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0"
  val slick = "com.typesafe.slick" %% "slick" % slickVersion
  val playSlick = "com.typesafe.play" %% "play-slick" % playSlickVersion
  val postgresql = "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

  val appDependencies = Seq(
    akka,
    jdbc,
    jodaTime,
    jodaTimeConvert,
    jodaTimeMapper,
    playSlick,
    postgresql,
    slick,
    ws
  )


  val main = Project(appName, file("."))
    .enablePlugins(play.PlayScala)
    .settings(
      libraryDependencies ++= appDependencies,
      resolvers := Seq(
        "Maven Central" at "http://repo1.maven.org/maven2/",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
      ),
      scalacOptions := Seq("-feature", "-deprecation"),
      scalaVersion := "2.10.4",
      version := appVersion
    )


}
            
