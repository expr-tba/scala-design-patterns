package com.bia.andersson.common

trait Ping {
  def ping(): Unit = System.out.println("ping")
}

trait Pong {
  def pong(): Unit = System.out.println("pong")
}

trait PingPong extends Ping with Pong {
  def pingPong(): Unit = {
    ping()
    pong()
  }
}

object MixinRunner extends PingPong {
  def main(args: Array[String]): Unit = {
    pingPong()
  }
}
