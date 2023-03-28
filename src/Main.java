import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception{

        // load environment variables from .env file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(".env");
        prop.load(fis);

        String apiKey = prop.getProperty("API_KEY");
        //- make http connection and get 250 top movies /////////////////////////////////////////
        //https://imdb-api.com/en/API/Top250Movies/k_6k2swk8s
        String url = "https://imdb-api.com/en/API/Top250Movies/"+ apiKey;
        URI address = URI.create(url);
       //HttpClient client = HttpClient.newHttpClient();//can only declare var if Java can find Type auto
        var client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder(address).GET().build();
       HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
       String body = response.body();


        //extract data ///////////////////////////////////////////////////////////////////////////
        var parser = new JsonParser();
        List <Map <String, String>> movieList = parser.parse(body);

        System.out.println(movieList.size());//list size
        System.out.println(movieList.get(0));//get item 0

        //show data //////////////////////////////////////////////////////////////////////////////
        for (Map<String,String> movie : movieList){
            System.out.println("\u001B[1mTitle : " + movie.get("title") + "\u001B[0m");
            System.out.println("Image : " +movie.get("image") );
            System.out.println("\u001B[34mRating : " +movie.get("imDbRating") + "\u001B[0m");
            int rate = Math.round(Float.parseFloat(movie.get("imDbRating")));
            for (int i = 0; i <= rate ; i++){
                System.out.print("\u001B[43m\u001B[30m*\u001B[0m\u001B[0m");
            }
            System.out.println("\n-----------------------------------------");
        }
    }
}