import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements ContentExtractor {

    public List<Content> extractContent(String json) {

        //extract data from string json
        var parser = new JsonParser();
        List<Map<String, String>> parsedList = parser.parse(json);

        List<Content> contentsList = new ArrayList<>();

        //populate content with parsed list
        for (Map<String, String> attributes : parsedList) {
            String title = attributes.get("title");
            String imageUrl = attributes.get("image")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var content = new Content(title, imageUrl);

            contentsList.add(content);

        }

        return contentsList;
    }

//    public int rate()

    public String rate(int rate) {
        if (rate >= 8) {
            return "EXCELENT!";
        } else {
            return "MEH";
        }


    }
}
