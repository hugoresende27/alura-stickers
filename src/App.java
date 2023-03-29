import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception{

        // load environment variables from .env file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(".env");
        prop.load(fis);

        String apiKeyIMDB = prop.getProperty("API_KEY_IMDB");
        String apiKeyNASA = prop.getProperty("API_KEY_NASA");
        //- make http connection and get 250 top movies /////////////////////////////////////////
        //https://imdb-api.com/en/API/Top250Movies/k_6k2swk8s
        String url = "https://imdb-api.com/en/API/Top250Movies/"+ apiKeyIMDB;

        // -nasa api https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY ///////////////////////////
        String url2 = "https://api.nasa.gov/planetary/apod?api_key="+ apiKeyNASA+ "&start_date=2022-06-12&end_date=2022-06-14";

        URI address = URI.create(url2);
       //HttpClient client = HttpClient.newHttpClient();//can only declare var if Java can find Type auto
        var client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder(address).GET().build();
       HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
       String body = response.body();


        System.out.println(body);

        //extract data ///////////////////////////////////////////////////////////////////////////
        var parser = new JsonParser();
        List <Map <String, String>> contentList = parser.parse(body);

        System.out.println(contentList.size());//list size
        System.out.println(contentList.get(0));//get item 0

        //show data //////////////////////////////////////////////////////////////////////////////
        int size = contentList.size();
        for (int i = 0; i < size ; i++){
//            Map<String, String> movie = movieList.get(i);
            Map<String, String> content = contentList.get(i);
//        for (Map<String,String> movie : movieList){

            System.out.println("\u001B[1mTitle : " + content.get("title") + "\u001B[0m");
            System.out.println("Image : " +content.get("url") );

            //for imdb
            /*
            System.out.println("\u001B[34mRating : " +movie.get("imDbRating") + "\u001B[0m");
            int rate = Math.round(Float.parseFloat(movie.get("imDbRating")));
            for (int j = 0; j <= rate ; j++){
                System.out.print("\u001B[43m\u001B[30m*\u001B[0m\u001B[0m");
            }
             */
            System.out.println("\n-----------------------------------------");

            //alura regex
            String urlImage = content.get("url").replaceAll("(@+)(.*).jpg$","$1.jpg");

            /*
            //to IMDB
            String urlImage = movie.get("url");
            int indexToCut = urlImage.indexOf('@');
            String cleanedUrl = urlImage.substring(0, indexToCut + 2);
            String urlImageFinal = cleanedUrl + ".jpg";
            System.out.println(urlImageFinal);
            System.out.println(urlImage);
            int textSize = movie.get("title").length();
            String fileName =  movie.get("title") + ".png";
             */

            String fileName =  content.get("title").substring(0, 3) + ".png";

            System.out.println(fileName);
            InputStream inputStrem = new URL(urlImage).openStream();
            var genImage = new ImageGenerator();
            genImage.create(inputStrem, fileName);
//            System.exit(0);
        }
    }
}