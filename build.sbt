val toolkitV = "0.7.0"
val toolkit = "org.scala-lang" %% "toolkit" % toolkitV
val toolkitTest = "org.scala-lang" %% "toolkit-test" % toolkitV

val PekkoVersion = "1.1.3"
libraryDependencies += ("org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion)
libraryDependencies += ("org.apache.pekko" %% "pekko-actor-testkit-typed" % PekkoVersion % Test)

ThisBuild / scalaVersion := "3.3.4"
libraryDependencies += toolkit
libraryDependencies += (toolkitTest % Test)
libraryDependencies += "com.lihaoyi" %% "cask" % "0.9.7"
libraryDependencies += ("org.automerge" % "automerge" % "0.0.7")
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.19"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test

addSbtPlugin("ch.epfl.scala" % "sbt-bloop" % "2.0.8")

fork := true
