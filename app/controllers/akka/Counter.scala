package controllers.akka

import akka.actor._

class Counter extends Actor {

  def count(n: Int) : Receive = {
    case "incr" => context.become(count(n + 1))
    case "get" => sender ! n 
  }
  def receive : Receive = count(0)

}