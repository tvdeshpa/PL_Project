import scala.util.Random

object MergeSortSequential extends App {

  var size = 1000000
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





}