package com.bia.andersson.linearisation

class MultiplierIdentity {
  def identity: Int = 1
}

trait DoubledMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 2 * super.identity
}

trait TripledMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 3 * super.identity
}

class ModifiedIdentity
    extends DoubledMultiplierIdentity
    with TripledMultiplierIdentity

class ModifiedIdentity2
    extends DoubledMultiplierIdentity
    with TripledMultiplierIdentity {
  override def identity: Int = super[DoubledMultiplierIdentity].identity
}

class ModifiedIdentity3
    extends DoubledMultiplierIdentity
    with TripledMultiplierIdentity {
  override def identity: Int = super[TripledMultiplierIdentity].identity
}

class ModifiedIdentity4
    extends TripledMultiplierIdentity
    with DoubledMultiplierIdentity

class ModifiedIdentity5
    extends TripledMultiplierIdentity
    with DoubledMultiplierIdentity {
  override def identity: Int = super[DoubledMultiplierIdentity].identity
}

class ModifiedIdentity6
    extends TripledMultiplierIdentity
    with DoubledMultiplierIdentity {
  override def identity: Int = super[TripledMultiplierIdentity].identity
}

object ModifiedIdentityUser extends App {
  val instance1 = new ModifiedIdentity
  val instance2 = new ModifiedIdentity2
  val instance3 = new ModifiedIdentity3
  val instance4 = new ModifiedIdentity4
  val instance5 = new ModifiedIdentity5
  val instance6 = new ModifiedIdentity6

  System.out.println(s"Result 1: ${instance1.identity}")
  System.out.println(s"Result 2: ${instance2.identity}")
  System.out.println(s"Result 3: ${instance3.identity}")
  System.out.println(s"Result 4: ${instance4.identity}")
  System.out.println(s"Result 5: ${instance5.identity}")
  System.out.println(s"Result 6: ${instance6.identity}")
}
