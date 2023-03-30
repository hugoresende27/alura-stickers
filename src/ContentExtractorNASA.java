import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorNASA implements ContentExtractor {

    public List<Content> extractContent(String json) {

        //extract data from string json
        var parser = new JsonParser();
        List<Map<String, String>> parsedList = parser.parse(json);

        List<Content> contentsList = new ArrayList<>();

        //populate content with parsed list
        for (Map<String, String> attributes : parsedList) {
            String title = attributes.get("title");
            String imageUrl = attributes.get("url");
            var content = new Content(title, imageUrl);
            contentsList.add(content);

        }

        return contentsList;
    }
}
