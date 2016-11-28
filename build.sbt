name := "OmahaHiLo"

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1"
libraryDependencies += "org.jetbrains" % "annotations" % "15.0"
libraryDependencies += "org.scalacheck" % "scalacheck_2.12" % "1.13.4"

mainClass in Compile := Some("com.highbar.cards.poker.omahaHiLo.OmahaHiLo")