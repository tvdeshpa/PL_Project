import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinRouter

class HelloActor extends Actor {

  def longRunningOperation(msg : String, sleep: Int): Unit = {
    Thread.sleep(sleep);
    println("Message from client :: " + msg)
  }

  def receive = {
    case (msg: String, sleep: Int) => sender ! longRunningOperation(msg, sleep)
    case _       => println("huh?")
  }
}

object Main extends App {
  val parallelSystem = ActorSystem("HelloSystem")

  // default Actor constructor
  val parallelActors = parallelSystem.actorOf(Props[HelloActor].withRouter(new RoundRobinRouter(4))) //.withRouter(new RoundRobinRouter(4))
  //helloActor ! ("Adithya", 1000)
  parallelActors.tell(("Akhil", 1000), Actor.noSender)
  parallelActors.tell(("Tushar", 1000), Actor.noSender)
  parallelActors.tell(("Nikitha", 1000), Actor.noSender)
  parallelActors.tell(("Gayathri", 1000), Actor.noSender)
  parallelActors.tell(1, Actor.noSender)

  println("END")
}