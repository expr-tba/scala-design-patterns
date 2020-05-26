package com.bia.andersson.basic

import com.bia.andersson.common.Notifier

class NotifierImpl(val notificationMessage: String) extends Notifier {
  override def clear(): Unit = System.out.println("cleared")
}
