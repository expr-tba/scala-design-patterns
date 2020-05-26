package com.bia.andersson.composition.self_types

import com.bia.andersson.common.Notifier
import com.bia.andersson.composition.{AlarmNotifier, Watch}

object SelfTypeWatchUser {
  def main(args: Array[String]): Unit = {
    val watch = new Watch("alarm with notification", 1000L)
      with AlarmNotifier
      with Notifier {
      override def trigger(): String = "Alarm triggered."

      override val notificationMessage: String = "The notification."

      override def clear(): Unit = System.out.println("Alarm cleared")
    }

    System.out.println(watch.trigger())
    watch.printNotification()
    System.out.println(s"The time is ${watch.getTime}")
    watch.clear()
  }
}
