import MergeSortSequential.merge
import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.RoundRobinRouter

import scala.concurrent.{Await, Future}
import scala.util.Random
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class SortActor extends Actor {

  def mergeSort(items: Vector[Int]): Vector[Int] = {
    if(items.size == 1)
      items
    else {
      val (left, right) = items.splitAt(items.length / 2)
      merge(mergeSort(left), mergeSort(right))
    }

  }

  def merge(left: Vector[Int], right: Vector[Int]): Vector[Int] = {
    if(right.size == 0 && left.size > 0)
      left
    else if(left.size == 0 && right.size > 0)
      right
    else {
      if(left.head < right.head)
        left.head +: merge(left.tail, right)
      else
        right.head +: merge(left, right.tail)
    }
  }

  override def receive: Receive = {
    case list:Vector[Int] => {
      sender ! mergeSort(list)
      println("Complete at " + System.currentTimeMillis())
    }
    case _ => println("Error")
  }
}

object MergeSortActorsMain extends App {

  var size = 10000000
  var array1 = Vector[Int]()

  val arrayItems = Vector.fill(size)(Random.nextInt(1000))

  val gList : List[Vector[Int]] = List(arrayItems, arrayItems, arrayItems)
  val system = ActorSystem("SortActor")
  val actor = system.actorOf(Props[SortActor].withRouter(RoundRobinRouter(4)))

  val t1 = size seconds
  implicit val timeout: Timeout = t1
  val startTime = System.currentTimeMillis();
  println(" start time : " + startTime)

  val futures = Future.traverse(gList)(items => {
      actor.ask(items)
  })
  Await.result(futures, t1).asInstanceOf[List[List[Int]]]

  val endTime = System.currentTimeMillis();
  val duration = endTime - startTime
  println(" Time taken for Parallel : " + duration)

}
