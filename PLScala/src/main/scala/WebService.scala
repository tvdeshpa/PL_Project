import java.net.URL

import scala.io.Source
import scala.util.parsing.json.JSON

class WebService {
  val API_KEY = "b1b15e88fa797225412429c1c50c122a1"
  def webServiceCall(zipCode: Int): String = {
    val jsonString = Source.fromURL(new URL("http://samples.openweathermap.org/data/2.5/weather?zip=" + zipCode +
                                              ",us&appid=" + API_KEY)).mkString
    jsonString
  }

  def extractTempFromJson(jsonString: String): String = {
    val jSONObject = JSON.parseFull(jsonString);
    val mainMap = jSONObject match {
      case Some(m: Map[String, Any]) => m("main") match {
        case s: Map[String, Any] => s
      }
    }
    mainMap("temp").toString
  }
}

class WebServiceProcessor {



  def sequentialProcessing(zipCodeList: List[Int]): Unit = {
    val webObj = new WebService()

    val startTime = System.currentTimeMillis()
    zipCodeList.foreach(zipCode => {
      val resp = webObj.webServiceCall(zipCode)
      val temp = webObj.extractTempFromJson(resp)
      println(zipCode + " temperature is : "+ temp)
    })
    val endTime = System.currentTimeMillis()

    println("Sequential Time Taken : "+ (endTime - startTime))
  }

  def parallelListProcessing(zipCodeList: List[Int]): Unit = {
    val webObj = new WebService()

    val startTime = System.currentTimeMillis()
    zipCodeList.par.foreach(zipCode => {
      val resp = webObj.webServiceCall(zipCode)
      val temp = webObj.extractTempFromJson(resp)
      println(zipCode + " temperature is : "+ temp)
    })
    val endTime = System.currentTimeMillis()

    println("Parallel Time Taken : "+ (endTime - startTime))
  }


}

object WebMain extends App {
  val zipCodeList = List(46202, 46203, 46204, 46205, 46206)
  val processor = new WebServiceProcessor()

  processor.sequentialProcessing(zipCodeList)
  processor.parallelListProcessing(zipCodeList)
  println("END")
}