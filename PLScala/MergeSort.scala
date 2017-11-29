import java.util.Random


object MergeSort extends App {

  var size = 1000
  var array1 = new Array[Int](size)

  var x = new Random()

  for(i <- (0 to size-1)){
      array1(i) = x.nextInt(1000)
  }

  var result = new Array[Int](1000)

  val startTime = System.currentTimeMillis();
  result = mergeSort(array1, 0, array1.length-1)
  val endTime = System.currentTimeMillis();
  val duration = endTime - startTime
  println(" Time taken for sequential : " + duration)



  def mergeArray(array1: Array[Int], l: Int, m: Int, r: Int) : Array[Int] = {

    var z = new Array[Int](1000)
    for(i <- (0 to size-1)){
      z(i) = array1(i)
    }

   var i = l
   var j = m + 1
   var k = l

    while ( {
      i <= m && j <= r
    }) {
      if (z(i) <= z(j)) {
        array1(k) = z(i)
        i += 1
      }
      else {
        array1(k) = z(j)
        j += 1
      }
      k += 1
    }
    // Copy the rest of the left side of the array into the target array
    while ( {
      i <= m
    }) {
      array1(k) = z(i)
      k += 1
      i += 1
    }

    z
  }

  def mergeSort(array1: Array[Int], l: Int, r: Int) : Array[Int] ={

    var y = new Array[Int](1000)

    if(l < r){
      var m = (l+r)/2
      mergeSort(array1, l, m)
      mergeSort(array1, m+1, r)

      y = mergeArray(array1, l, m, r)
    }
    y
  }





}
