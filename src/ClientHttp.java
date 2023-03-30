import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    public String searchData(String url)  {

        try {
//            URI address = URI.create(url);
            //HttpClient client = HttpClient.newHttpClient();//can only declare var if Java can find Type auto
            var client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException ex) {
//            throw new RuntimeException(ex);
            throw new ClientHttpException("Error getting data from url, check your URL, is not correct...");
        }

    }
}
