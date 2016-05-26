import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class WeatherTester{
	public static void main(String [] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city name or zip code you to find Weather: ");
		String chosenLocation = sc.next();
		Weather newWeather = new Weather(chosenLocation);
		newWeather.getWeatherData();
		String cityName = newWeather.getLocation();
		System.out.println(newWeather.toString());
		


		/*System.out.println("temperature in " + cityName + " is " + temperatureInFehrenheit);
		System.out.println( newWeather.gettempInCel());*/
 
	}
}