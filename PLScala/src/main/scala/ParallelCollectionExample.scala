object ParallelCollectionExample extends App {
  (1 to 5).foreach(x => {
    Thread.sleep(1000)
    println(x)
  }
  )

  (1 to 5).par.foreach(x => {
    Thread.sleep(1000)
    println(x)
  }
  )

}
