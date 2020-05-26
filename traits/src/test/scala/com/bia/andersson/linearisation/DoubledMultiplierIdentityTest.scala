package com.bia.andersson.linearisation

import org.scalatest.{FlatSpec, ShouldMatchers}

class DoubledMultiplierIdentityTest extends FlatSpec with ShouldMatchers {
  class DoubledMultiplierIdentityClass extends DoubledMultiplierIdentity

  val instance = new DoubledMultiplierIdentityClass

  "identity" should "return 2 * 1" in {
    instance.identity should equal(2)
  }
}
