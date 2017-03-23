package com.rabbitmq

import com.typesafe.config.ConfigFactory
import java.io.File

/**
  * Created by BC0414 on 23/03/17.
  */
final class RabbitMQConfig(configPath : String) {


  val myConfigFile = new File(configPath)

  val path = myConfigFile.getAbsolutePath()


  val fileConfig = ConfigFactory.parseFile(myConfigFile).getConfig("rabbitmq-config")


  protected val config = ConfigFactory.load(fileConfig)

  val sslStatus = config.getString("ssl.status").toBoolean
  val connectionTestOnly = config.getString("connectionTestOnly").toBoolean
  val sslOneWay = config.getString("ssl.oneway").toBoolean
  val trustStore = config.getString("ssl.truststore")
  val trustStorePassword = config.getString("ssl.trustStorePassword")
  val keystore = config.getString("ssl.keystore")
  val keystorePassword = config.getString("ssl.keystorePassword")
  val hostname = config.getString("hostname")
  val port =config.getString("port")
  val queueName = config.getString("queuename")
  val userName = config.getString("rabbitmq.username")
  val userPassword = config.getString("rabbitmq.password")

}
