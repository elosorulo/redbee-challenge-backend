import sbt.Keys._
import sbt._
import sbtrelease.Version

name := "redbee-challenge-backend"

resolvers += Resolver.sonatypeRepo("public")
scalaVersion := "2.12.2"
releaseNextVersion := { ver => Version(ver).map(_.bumpMinor.string).getOrElse("Error") }
assemblyJarName in assembly := "redbee-challenge-backend.jar"

libraryDependencies ++= Seq(
  //AWS Lambda
  "com.amazonaws" % "aws-lambda-java-events" % "1.3.0",
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
  //Json Parsing
  "io.circe" % "circe-core_2.12" % "0.9.1",
  "io.circe" % "circe-generic_2.12" % "0.9.1",
  "io.circe" % "circe-parser_2.12" % "0.9.1",
  //Unit Testing
  "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
  "org.mockito" % "mockito-core" % "2.7.22",
  //Dependency Injection
  "net.codingwell" % "scala-guice_2.12" % "4.1.0",
  //Dynamodb
  "com.gu" % "scanamo_2.12" % "1.0.0-M3"

)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings")
