import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaExtractor implements ContentExtractor{
    
    public List<Content> contentExtractor (String json) {

        // Filter the data from movies (Title, Rate, Poster)
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        //fill in the list
        for (Map<String, String> attribute : attributeList) {
            String title = attribute.get("title");
            String urlImage = attribute.get("url");
            
            var content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;
    }
}
