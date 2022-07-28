import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo{

    public List<Conteudo> extractorOfContent (String json) {
        
        // Filter the data from movies (Title, Rate, Poster)
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Conteudo> contents = new ArrayList<>();

        //fill the list
        for (Map<String, String> attribute : attributeList) {
            String title = attribute.get("title");
            String urlImage = attribute.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");;
            //System.out.println(urlImage);
            //Float.parseFloat(rateString);
            //String rateString = content.get("imDbRating");
            var content = new Conteudo(title, urlImage);
            contents.add(content);
            
        }

        return contents;
    }

}
