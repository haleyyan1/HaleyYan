import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIWeatherClient {

    public static String makeAPICall(String ip) throws java.io.IOException, InterruptedException{
        try{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q="+ip))
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .header("X-RapidAPI-Key", "b9185024a2mshc2cd7b37131cff8p173310jsn3bc6cc53e969")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();}
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
