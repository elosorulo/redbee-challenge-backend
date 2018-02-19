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
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.9.4"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings")
