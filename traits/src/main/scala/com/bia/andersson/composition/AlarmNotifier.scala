package com.bia.andersson.composition

import com.bia.andersson.common.Notifier

trait AlarmNotifier {
  this: Notifier =>
  def trigger(): String
}
