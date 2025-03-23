val toolkitV = "0.7.0"
val toolkit = "org.scala-lang" %% "toolkit" % toolkitV
val toolkitTest = "org.scala-lang" %% "toolkit-test" % toolkitV

val PekkoVersion = "1.1.3"
libraryDependencies += ("org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion)
  .cross(
    CrossVersion.for3Use2_13
  )

ThisBuild / scalaVersion := "3.3.4"
libraryDependencies += toolkit
libraryDependencies += (toolkitTest % Test)
libraryDependencies += "com.lihaoyi" %% "cask" % "0.9.7"
libraryDependencies += ("org.automerge" % "automerge" % "0.0.7")

fork := true
