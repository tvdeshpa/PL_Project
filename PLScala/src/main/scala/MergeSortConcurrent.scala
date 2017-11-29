import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.RoundRobinRouter
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Random

class MergeSortActor extends Actor {


  def mergeSort(items: Vector[Int]): Vector[Int] = {
    if(items.size == 1)
      items
    else {
      val (left, right) = items.splitAt(items.length / 2)
      merge(mergeSort(left), mergeSort(right))
    }

  }

  def merge(left: Vector[Int], right: Vector[Int]): Vector[Int] = {
    var leftIndex = 0
    var rightIndex = 0
    var merged = Vector[Int]()
    var mergedIndex = 0
    while (leftIndex < left.length && rightIndex < right.length) {
      if (left(leftIndex) <= right(rightIndex)) {
        merged :+= left(leftIndex)
        leftIndex += 1
      } else {
        merged :+= right(rightIndex)
        rightIndex += 1
      }
    }
    if (leftIndex == left.length) {
      merged ++ right.slice(rightIndex, right.length)
    } else {
      merged ++ left.slice(leftIndex, left.length)
    }
  }

  def receive = {
    case items: Vector[Int] => sender ! mergeSort(items)
    case (left: Vector[Int], right: Vector[Int]) => sender ! merge(left, right)
  }
}

object MergeSortConcurrent extends App {



  def startMergeSort(arrayItems: Vector[Int], numOfItems: Int, numOfWorker: Int): Unit = {
    val partitionSize = (numOfItems / numOfWorker).ceil.toInt
    var partitions: List[Vector[Int]] = arrayItems.grouped(partitionSize).toList
    val timeout_ = numOfItems seconds
    implicit val timeout: Timeout = timeout_
    val system = ActorSystem("MergeSortActor")
    val actor = system.actorOf(Props[MergeSortActor].withRouter(RoundRobinRouter(numOfWorker)))

    var futures = Future.traverse(partitions)(items => {
      actor.ask(items)
    })

    partitions = Await.result(futures, timeout_).asInstanceOf[List[Vector[Int]]]

    while (partitions.length > 1) {
      if (partitions.length % 2 == 1) {
        partitions = partitions :+ Vector[Int]()
      }
      futures = Future.traverse(partitions.grouped(2).toList)(items => {
        actor.ask((items(0), items(1)))
      })
      partitions = Await.result(futures, timeout_).asInstanceOf[List[Vector[Int]]]
    }
  }

  val numOfItems = 1000000
  val numOfWorker = 100
  val arrayItems = Vector.fill(numOfItems)(Random.nextInt(1000))

  val startTime = System.currentTimeMillis();
  startMergeSort(arrayItems, numOfItems, numOfWorker)
  startMergeSort(arrayItems, numOfItems, numOfWorker)
  startMergeSort(arrayItems, numOfItems, numOfWorker)
  val endTime = System.currentTimeMillis();
  println("Time taken :: "+ (endTime - startTime))

}
