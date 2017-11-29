import scala.util.Random

object MergeSortSequential extends App {

  var size = 10000000
  var array1 = Vector[Int]()

  val arrayItems = Vector.fill(size)(Random.nextInt(1000))


  val startTime = System.currentTimeMillis();
  mergeSort(arrayItems)
  mergeSort(arrayItems)
  mergeSort(arrayItems)
  val endTime = System.currentTimeMillis();
  val duration = endTime - startTime
  println(" Time taken for sequential : " + duration)



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

}
