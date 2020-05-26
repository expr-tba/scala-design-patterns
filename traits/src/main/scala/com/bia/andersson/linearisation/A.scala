package com.bia.andersson.linearisation

trait A {
  def hello: String        = "Hello, I am trait A"
  def pass(a: Int): String = s"Trait A said: you passed $a"
}

trait B {
  def hello: String         = "Hello, I am trait B"
  def value(a: Int): String = a.toString
}

object Clashing extends App with A with B {
  override def hello: String = super[A].hello
  System.out.println(hello)
}
