import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.RoundRobinRouter

class PerfectNumberActors extends Actor{

  def sumOfFactorsImmutable(number: Int): Int = {
    (1 to number).foldLeft(0) {
      (sum, i) => if (number % i == 0) sum + i else sum
    }
  }

  def sumOfFactors(number: Int): Int = {
    var sum = 0
    (1 to number).foreach(x => {
      if(number % x ==0)
        sum = sum + x
    })
    sum
  }

  def isPerfectNumer(number: Int): Boolean = {
    2*number == sumOfFactors(number)
  }

  def receive = {
    case number: Int => println(number + " is a perfect number ? " + isPerfectNumer(number) + " " +
                                System.currentTimeMillis())
    case _ => println("Exception :: ")
  }
}

object PerfectNumberConcurrent extends App {
  val system = ActorSystem("PerfectNumberActor")
  val actor = system.actorOf(Props[PerfectNumberActors].withRouter(RoundRobinRouter(4)))
  val startTime = System.currentTimeMillis();
  println(startTime)
  actor.tell(6, Actor.noSender)
  actor.tell(533550336, Actor.noSender)
  actor.tell(633550337, Actor.noSender)
  actor.tell(935503371, Actor.noSender)
  actor.tell(735503371, Actor.noSender)
}