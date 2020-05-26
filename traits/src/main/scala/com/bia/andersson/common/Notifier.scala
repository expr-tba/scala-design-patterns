package com.bia.andersson.common

trait Notifier {
  val notificationMessage: String

  def printNotification(): Unit = {
    System.out.println(notificationMessage)
  }

  def clear()
}
