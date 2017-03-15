package com.rabbitmq

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLContext


/**
  * Created by Sarunas G. on 15/03/17.
  */
class Sender {

  private val QUEUE_NAME = "queue_test_rabbitmq"

  def main(args : Array[String]) {


    val factory = new ConnectionFactory
    factory.setUri("amqp://guest:guest@localhost")

    val supportedProtocols = SSLContext.getDefault.getSupportedSSLParameters.getProtocols
    factory.useSslProtocol(ConnectionFactory.computeDefaultTlsProcotol(supportedProtocols))

    var connection = None : Option[Connection]
    try {

      connection = Some(factory.newConnection())

      val channel = connection.get.createChannel()
      channel.queueDeclare(QUEUE_NAME, false, false, false, null)

      val message = "Sending message from Sender"

      channel.basicPublish("", QUEUE_NAME, null, message.getBytes)

      println("Message sent from sender to queue: " + message )

      channel.close()
      connection.get.close()

    } catch  {

      case ex : IOException =>
      println("RabbitMQ server is Down !")
      println(ex.getMessage)

      case ex: TimeoutException => ex.printStackTrace()
    }
  }

}
