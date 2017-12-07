import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WebServiceWorker extends Thread{

	private static final String API_KEY = "place your key here";
	int zipCode;
	public WebServiceWorker() {
		
	}
	
	public WebServiceWorker(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public void run() {
		try {
			System.out.println(getWeatherForZip(zipCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public String getWeatherForZip(int zipCode) throws Exception {
		String jsonResponse = weatherAPICall(zipCode);
    	return zipCode + " Temperature is : " + extractTempFromJson(jsonResponse);    
    }

    public  String weatherAPICall(int zipCode) throws Exception {
        URL url = new URL("http://samples.openweathermap.org/data/2.5/weather?zip="+ zipCode +",us&appid=" + API_KEY);

        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("Accept", "application/json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                (httpConnection.getInputStream())));

        String output;
        String jsonString = null;
        
        while ((output = bufferedReader.readLine()) != null) {
        	jsonString = output;
            //System.out.println(output);
        }

        httpConnection.disconnect();      
        return jsonString;
    }

    public String extractTempFromJson(String json) {

    	JSONParser parser = new JSONParser();
    	JSONObject jsonObject;
    	String temp = "";
    	double tempVal = 0;
		try {
			jsonObject = (JSONObject) parser.parse(json);
			JSONObject mainJson = (JSONObject) jsonObject.get("main");
			tempVal = (double) mainJson.get("temp");
		} catch (ParseException e) {
			temp = "Exception";
			
		}
    	
    	return temp + tempVal;
    }
}
