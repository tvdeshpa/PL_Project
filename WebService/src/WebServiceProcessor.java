import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// http://central.maven.org/maven2/com/googlecode/json-simple/json-simple/1.1.1/json-simple-1.1.1.jar
// Download and add this to build path

public class WebServiceProcessor {

    public static void main(String[] args) {

        try {
        	
        	ArrayList<Integer> zipCodeList = new ArrayList<>();
        	zipCodeList.add(46202);
        	zipCodeList.add(46220);
        	zipCodeList.add(46524);
        	zipCodeList.add(46234);
        	zipCodeList.add(46245);
        	
        	System.out.println("Sequential Execution ********");
        	WebServiceWorker worker = new WebServiceWorker();
        	long seqStartTime = System.nanoTime();
        	for(int zipCode : zipCodeList) {
        		System.out.println(worker.getWeatherForZip(zipCode));
        	}                  
            long seqEndTime = System.nanoTime();
            
            ExecutorService executor = Executors.newFixedThreadPool(5);
            System.out.println("Parallel Execution ********");
            long parStartTime = System.nanoTime();
            for(int zipCode: zipCodeList) {
                Runnable webWorker = new WebServiceWorker(zipCode);
                executor.execute(webWorker);
              }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }            
           long parEndTime = System.nanoTime();
           
           System.out.println("Sequential Time Taken :: "+ (seqEndTime - seqStartTime));
           System.out.println("Parallel Time Taken   :: "+ (parEndTime - parStartTime));
            

        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
   
}