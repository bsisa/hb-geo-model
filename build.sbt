name := """hb-geo-model"""

organization  := """ch.bsisa"""

version := "1.0"

// ========== Targets Scala 2.11 - Play 2.5     ==========  
//scalaVersion := "2.11.8"
// Used to perform model to json conversions. (Note play-functional we also use in the current project is a transitive dependency of play-json)
//libraryDependencies += "com.typesafe.play" %% "play-json" % "2.5.3"


// ========== Targets Scala 2.10 - Play 2.2-2.4 ==========
scalaVersion := "2.10.6"
// Used to perform model to json conversions. (Note play-functional we also use in the current project is a transitive dependency of play-json)
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.8"


// ========== Common =====================================
// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// specs2-scalacheck contains required specs2.mutable package missing in specs2 alone.
libraryDependencies += "org.specs2" %% "specs2-scalacheck" % "3.8.4.1-scalaz-7.1" % "test"
//libraryDependencies += "org.specs2" %% "specs2" % "3.3.1" % "test"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
