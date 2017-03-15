name := "RabbitMQTest"

version := "1.0"

scalaVersion := "2.11.8"


resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases")

libraryDependencies ++= Seq("com.rabbitmq" % "amqp-client" % "4.0.2")