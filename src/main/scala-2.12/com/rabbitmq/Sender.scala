package com.rabbitmq

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLContext

import scala.util.Try


/**
  * Created by Sarunas G. on 15/03/17.
  */
object Sender {


  def main(args: Array[String]) {

    val configPath = Try(args(0))
    val conf = new RabbitMQConfig(configPath.getOrElse("./src/main/resources/application.conf"))
    import conf._

    val QUEUE_NAME = queueName

    val factory = new ConnectionFactory
    factory.setUri(s"amqp://$userName:$userPassword@$hostname")

    if (sslStatus) {
      val supportedProtocols = SSLContext.getDefault.getSupportedSSLParameters.getProtocols
      factory.useSslProtocol(ConnectionFactory.computeDefaultTlsProcotol(supportedProtocols))
    }

    var connection = None: Option[Connection]
    try {

      connection = Some(factory.newConnection())
      println("Connection status: " + connection.get.isOpen)


      if (!connectionTestOnly) {

        val channel = connection.get.createChannel()
        channel.queueDeclare(QUEUE_NAME, false, false, false, null)

        val message = s"Message has been sent to $QUEUE_NAME"

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes)

        println("Message sent from sender to queue: " + message)

        channel.close()
      }

      connection.get.close()
      println("Connection status: " + connection.get.isOpen)


    } catch {

      case ex: IOException =>
        println("RabbitMQ server is Down !")
        println(ex.getMessage)

      case ex: TimeoutException => ex.printStackTrace()
    }
  }

}
