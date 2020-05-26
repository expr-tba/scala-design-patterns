package com.bia.andersson.common

trait Alarm {
  trait Alarmer {
    def trigger(): String
  }
  def alarm: Alarmer
}
