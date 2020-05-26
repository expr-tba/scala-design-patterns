package com.bia.andersson.composition

import com.bia.andersson.common.{Alarm, ConnectorWithHelper, Notifier}

class Watch(brand: String, initialTime: Long) {
  def getTime: Long = System.currentTimeMillis() - initialTime
}

object WatchUser {
  def main(args: Array[String]): Unit = {
    val expensiveWatch =
      new Watch("expensive brand", 1000L) with Alarm with Notifier {
        override def trigger(): String = "The alarm was triggered"

        override val notificationMessage: String = "Alarm is running!"

        override def clear(): Unit = System.out.println("Alarm cleared.")
      }

    val cheapWatch = new Watch("cheap brand", 1000L) with Alarm {
      override def trigger(): String = "The alarm was triggered."
    }

    System.out.println(expensiveWatch.trigger())
    expensiveWatch.printNotification()
    System.out.println(s"The time is ${expensiveWatch.getTime}")
    expensiveWatch.clear()

    System.out.println(cheapWatch.trigger())
    System.out.println("Cheap watches cannot manually stop the alarm..")
  }
}

object ReallyExpensiveWatchUser {
  def main(args: Array[String]): Unit = {
    val reallyExpensiveWatch =
      new Watch("really expensive brand", 1000L) with ConnectorWithHelper {
        override def connect(): Unit =
          System.out.println("Connected with another connector")

        override def close(): Unit =
          System.out.println("Closed with another connector")
      }

    System.out.println("Using the really expensive watch.")
    reallyExpensiveWatch.findDriver()
    reallyExpensiveWatch.connect()
    reallyExpensiveWatch.close()
  }
}
