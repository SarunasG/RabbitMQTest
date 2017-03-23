name := "RabbitMQTest"

version := "1.0"

//scalaVersion := "2.11.8"
scalaVersion in ThisBuild := "2.12.1"


resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases",
  "Bintray sbt plugin releases" at "http://dl.bintray.com/sbt/sbt-plugin-releases")


libraryDependencies ++= Seq("com.rabbitmq" % "amqp-client" % "4.0.2",

  "com.typesafe" % "config" % "1.3.1")



assemblyMergeStrategy in assembly := {

  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first

}
