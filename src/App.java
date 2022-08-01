import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        // Get Dataset
        String apiName = "IMDB_MOVIES"; // IMDB_MOVIES || NASA
        List<String> listWithRate = Arrays.asList("IMDB_MOVIES");
        var properties = new readPropriedades();
        String url = properties.getAPIUrl(apiName); 

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        // IMDB API
        ContentExtractor extractor = new IMDbExtractor();

        // NASA API
        // ContentExtractor extractor = new NasaExtractor();

        List<Content> contents = extractor.contentExtractor(json);
        Boolean tag = listWithRate.contains(apiName) ? true : false;

        System.out.println("Does this API have the attribute RATE: " + tag);

        GeneratorOFStickers generator = new GeneratorOFStickers();
        GeneratorOfStickersRate generatorR = new GeneratorOfStickersRate();

        // Manipulate Data and show them
        
        for (int i=0; i<5;i++) {

            Content content = contents.get(i);
            
            String title = content.getTitle();
            InputStream inputStream = new URL(content.getUrlImage()).openStream();

            if(tag) {
                String rateString = content.getRateImage();
                generatorR.createStick(inputStream, rateString, title.replace(":","")+".png");
                System.out.println("\u001b[3mTitle:\u001b[m\u001b[32;1m "+title+"\u001b[m");
            } else {
                generator.createStick(inputStream, title.replace(":","")+".png");
                System.out.println("\u001b[3mTitle:\u001b[m\u001b[32;1m "+title+"\u001b[m");
            }
            
            
            
        }
    }
}
