import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.io.FileInputStream;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {

        // load environment variables from .env file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(".env");
        prop.load(fis);

//        String apiKeyIMDB = System.getenv("API_KEY_IMDB");
        String apiKeyIMDB = prop.getProperty("API_KEY_IMDB");
        String apiKeyNASA = prop.getProperty("API_KEY_NASA");
        //- make http connection and get 250 top movies /////////////////////////////////////////
        //https://imdb-api.com/en/API/Top250Movies/k_6k2swk8s
        String urlIMDB = "https://imdb-api.com/en/API/Top250Movies/" + apiKeyIMDB;
//        String url = "https://imdb-api.com/en/API/MostPopularMovies/"+ apiKeyIMDB;
//        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
//        String url ="https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        System.out.println(apiKeyIMDB);

        // -nasa api https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY ///////////////////////////
        String urlNASA = "https://api.nasa.gov/planetary/apod?api_key=" + apiKeyNASA + "&start_date=2022-06-12&end_date=2022-06-14";


        //ClientHttp CLASS ////////////////////////////////////////////
        var http = new ClientHttp();
//        String json = http.searchData(urlIMDB);
        String json = http.searchData(urlNASA);


        //extract data Content CLASS NASA ///////////////////////////////////////////////////////////////////////////
        ContentExtractorNASA extractor = new ContentExtractorNASA();

        //extract data Content CLASS IMDB ///////////////////////////////////////////////////////////////////////////
//        ContentExtractorIMDB extractor = new ContentExtractorIMDB();

        //create content list
        List<Content> contentsList = extractor.extractContent(json);
        var generator = new ImageGenerator();


        //show and manipulate data //////////////////////////////////////////////////////////////////////////////
        for (Content content : contentsList) {


//            System.out.println("\u001B[1mTitle : " + content.get("title") + "\u001B[0m");
//            System.out.println("Image : " + content.get("url"));

            //for imdb rate
            /*
            System.out.println("\u001B[34mRating : " +movie.get("imDbRating") + "\u001B[0m");
            int rate = Math.round(Float.parseFloat(movie.get("imDbRating")));
            for (int j = 0; j <= rate ; j++){
                System.out.print("\u001B[43m\u001B[30m*\u001B[0m\u001B[0m");
            }
             */
            System.out.println("\n-----------------------------------------");

            //to IMDB
//            String urlImage = content.get("image");
//            int indexToCut = urlImage.indexOf('@');
//            String cleanedUrl = urlImage.substring(0, indexToCut + 2);
//            String urlImageFinal = cleanedUrl + ".jpg";
//            System.out.println(urlImageFinal);
//            System.out.println(urlImage);
//            int textSize = content.get("title").length();
//            String fileName =  content.get("title") + ".png";   //NASA

//                Content content = contentsList.get(i);

            String title = content.title();
            String fileName = title.substring(0, 3) + ".png";//IMDB
//            String fileName = content.get("title").substring(0, 7) + ".png";//IMDB
//            double imdbRate = Double.parseDouble(content.get("imDbRating"));

            String comment = "";
            InputStream devImage;
//            if (imdbRate >= 8){
            comment = "EXCELENT!";
            devImage = new FileInputStream("my_images/good.png");
//            } else {
//                comment ="meh";
//                devImage = new FileInputStream("my_images/bad.png");
//            }


            InputStream inputStream = new URL(content.imageUrl()).openStream();

            System.out.println(title);
            generator.create(inputStream, fileName, comment, devImage);
//            System.exit(0);
        }
    }
}