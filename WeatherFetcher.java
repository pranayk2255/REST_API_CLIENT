import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class WeatherFetcher {

    public static void main(String[] args) throws Exception {
        
        String apiKey = "YOUR_API_KEY"; 
        String city = "Pune";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                     "&appid=" + apiKey + "&units=metric";

       
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

     
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      
        JSONObject json = new JSONObject(response.body());

    
        String cityName = json.getString("name");
        JSONObject main = json.getJSONObject("main");
        double temp = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
        String description = weather.getString("description");

       
        System.out.println("===== Weather Report =====");
        System.out.println("City: " + cityName);
        System.out.println("Temperature: " + temp + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Description: " + description);
        System.out.println("==========================");
    }
}