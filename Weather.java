import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
public class Weather{
	
	private String location = "";
	private double temperatureInCelcius = 0.0;
	private double temperatureInFehrenheit = 0.0;
	private String searchParameter = "";
	private String urlActual = "";
	private final String API_URL = "http://api.openweathermap.org/data/2.5/weather?";
	private final String API_KEY = "&appid=267a3fcae28328116ef24e074417a657";


	public Weather(String thelocation){
		location = thelocation;
		searchParameter = "q=";
	}

	public Weather (int zipCode){
		//do some api calls here
		location = Integer.toString(zipCode);
		searchParameter = "zip=";
	}

	private void createURL(){

		urlActual = API_URL + searchParameter + location + API_KEY;
		//System.out.println("calling weather api from URL = " + urlActual);
	}

	public void getWeatherData(){
		createURL();
		try{
			URL weatherURL = new URL(urlActual); //change name
			BufferedReader in = new BufferedReader( new InputStreamReader(weatherURL.openStream()));
			JSONParser parser = new JSONParser();		
			Object fromURL = parser.parse(in);
			JSONObject weatherData = (JSONObject) fromURL;
			JSONObject temperatureData = (JSONObject) weatherData.get("main");
			Double tempVar = (double) temperatureData.get("temp");
			Double compareTemp = tempVar.doubleValue();
			//System.out.println("data from json = " + temperatureData.get("temp"));
			temperatureInCelcius = ((double) temperatureData.get("temp")) - 273.15;
			
			//System.out.println("new casted data = " + temperatureInCelcius);
			temperatureInFehrenheit = temperatureInCelcius * 2 + 32;

		}
		catch (IOException ex1) {
			ex1.printStackTrace();
		}
		catch (ParseException ex2){
			System.out.println(ex2.getPosition()); 
		}		
	}


	public String getLocation(){
		return location;
	}

	private String formatTempNum(String patturn, double value){
		DecimalFormat formatter = new DecimalFormat(patturn);
		return formatter.format(value);

	}

	private String gettempInCel(){
		return formatTempNum("##.##",temperatureInCelcius);
		/*String celTemp = Double.toString(temperatureInCelcius);
		return celTemp;*/
	}
	private String getTempInFehr(){
		return formatTempNum("###,##", temperatureInFehrenheit);
		//return Double.toString(temperatureInFehrenheit);
	}

	@Override 
	public String toString(){
		return "The temperature in " + location + " is: " + gettempInCel() + " \u00B0C\tor " + getTempInFehr() + " \u00B0F";  
	}
	
}
