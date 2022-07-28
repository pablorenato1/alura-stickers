import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo{
    
    public List<Conteudo> extractorOfContent (String json) {

        // Filter the data from movies (Title, Rate, Poster)
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Conteudo> contents = new ArrayList<>();

        //fill the list
        for (Map<String, String> attribute : attributeList) {
            String title = attribute.get("title");
            String urlImage = attribute.get("url");
            
            var content = new Conteudo(title, urlImage);

            contents.add(content);
        }

        return contents;
    }
}
