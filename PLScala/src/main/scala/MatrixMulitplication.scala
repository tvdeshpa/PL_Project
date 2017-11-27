import java.util.Random

import Array._

object MatrixMultiplication extends App{

  val row = 1000
  val col = 1000
  var array1 = ofDim[Int](row, col)
  var array2 = ofDim[Int](row, col)

  var x = new Random()

  for(i <- (0 to row-1)){
    for(j <- (0 to col-1)){
      array1(i)(j) = x.nextInt()
    }
  }

  for(i <- (0 to row-1)){
    for(j <- (0 to col-1)){
      array2(i)(j) = x.nextInt()
    }
  }

  val startTime = System.currentTimeMillis();
  var result1 = sequentialMatrixMultiplication(array1, array2, row, col)
  val endTime = System.currentTimeMillis();
  val duration = endTime - startTime
  println(" Time taken for sequential : " + duration)

  val startTime2 = System.currentTimeMillis();
  var result4 = parallelMatrixMultiplication(array1, array2, row, col)
  val endTime2 = System.currentTimeMillis();
  val duration2 = endTime2 - startTime2
  println(" Time taken for Parallel : " + duration2)

  def sequentialMatrixMultiplication(array1:Array[Array[Int]] , array2:Array[Array[Int]],
                                     row:Int, col:Int): Array[Array[Int]] ={
    var result = ofDim[Int](row,col)

    for (i <- (0 to row - 1)) {
      for(j <- (0 to col -1 )){
        for(k <- (0 to row - 1)){
            result(i)(j) += array1(i)(k) * array2(k)(j)
        }
      }
    }
    result
  }


  def parallelMatrixMultiplication(array1:Array[Array[Int]] , array2:Array[Array[Int]],
                                   row:Int, col:Int): Array[Array[Int]] ={

    var result = ofDim[Int](row,col)

    (0 until row*row).par.foreach( x =>{
      val i = x/row
      val k = x % row
      var j = 0
      while(j<row){
        result(i)(j) += array1(i)(k) * array2(k)(j)
        j += 1
      }
    })
    result
  }




}
