object PerfectNumberSequential extends App {

 /* def sumOfFactors(number: Int): Int = {
    (1 to number).foldLeft(0) {
      (sum, i) => if (number % i == 0) sum + i else sum
    }
  } */


  def sumOfFactorsMutable(number: Int): Int = {
    var sum = 0
    (1 to number).foreach(x => {
      if(number % x ==0)
        sum = sum + x
    })
    sum
  }

  def sumOfFactorsParallelMutable(number: Int): Int = {
    var sum = 0
    (1 to number).par.foreach(x => {
      if(number % x ==0)
        sum = sum + x
    })
    sum
  }

  def isPerfectNumerSeq(number: Int): Boolean = {
    2*number == sumOfFactorsMutable(number)
  }

  def isPerfectNumerParallel(number: Int): Boolean = {
    2*number == sumOfFactorsParallelMutable(number)
  }

  val startTime = System.currentTimeMillis();
  println("6 is a perfect number ? " + isPerfectNumerSeq(6))
  println("533550336 is a perfect number ? " + isPerfectNumerSeq(33550336))
  println("633550337 is a perfect number ? " + isPerfectNumerSeq(633550337))
  println("935503371 is a perfect number ? " + isPerfectNumerSeq(935503371))
  println("735503371 is a perfect number ? " + isPerfectNumerSeq(735503371))
  val endTime = System.currentTimeMillis();

  println("Time Taken :: " + (endTime - startTime) / 1000)

  val startTimeP = System.currentTimeMillis();
  println("6 is a perfect number ? " + isPerfectNumerParallel(6))
  println("533550336 is a perfect number ? " + isPerfectNumerParallel(33550336))
  println("633550337 is a perfect number ? " + isPerfectNumerParallel(633550337))
  println("935503371 is a perfect number ? " + isPerfectNumerParallel(935503371))
  println("735503371 is a perfect number ? " + isPerfectNumerParallel(735503371))
  val endTimeP = System.currentTimeMillis();

  println("Time Taken :: " + (endTimeP - startTimeP) / 1000)
}