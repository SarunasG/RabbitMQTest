package com.rabbitmq

import com.rabbitmq.client._
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLContext

/**
  * Created by Sarunas G. on 15/03/17.
  */
class Receiver {

  private val QUEUE_NAME = "queue_1"

  def main(args: Array[String]) {

    val factory = new ConnectionFactory()
    factory.setUri("amqp://guest:guest@localhost")

    var connection = None: Option[Connection]
    try {

      connection = Some(factory.newConnection)

      val channel = connection.get.createChannel()
      channel.queueDeclare(QUEUE_NAME, false, false, false, null)

      println("Listening for messages: ")


      val consumer = new DefaultConsumer(channel) {

        override def handleDelivery(consumerTag: String, envelope: Envelope,
                                    properties: AMQP.BasicProperties, body: Array[Byte]) {
          val message = new String(body, "UTF-8")
          println("Message Received: " + message)
        }
      }

      channel.basicConsume(QUEUE_NAME, true, consumer);
    } catch {

      case ex: IOException =>
        println("RabbitMQ server is Down !")
        println(ex.getMessage)

      case ex: TimeoutException => ex.printStackTrace()

    }
  }


}
