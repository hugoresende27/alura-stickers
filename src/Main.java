import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{

        //- make http connection and get 250 top movies /////////////////////////////////////////
        //https://imdb-api.com/en/API/Top250Movies/k_6k2swk8s
        String url = "https://imdb-api.com/en/API/Top250Movies/k_6k2swk8s";
        URI address = URI.create(url);
       //HttpClient client = HttpClient.newHttpClient();//can only declare var if Java can find Type auto
        var client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder(address).GET().build();
       HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
       String body = response.body();


        //extract data ///////////////////////////////////////////////////////////////////////////
        var parser = new JsonParser();
        List <Map <String, String>> movieList = parser.parse(body);
        for (Map<String,String> x : movieList){
//            System.out.println(x);
            for (String i : x.values()){
                System.out.println(i);
            }
        }
    }
}