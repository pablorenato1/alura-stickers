import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        // Get Dataset
        var properties = new readPropriedades();
        String url = properties.getAPIUrl("IMDB_MOVIES"); // IMDB_MOVIES || NASA

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        // IMDB API
        ExtratorDeConteudo extractor = new ExtratorDeConteudoDoIMDB();

        // NASA API
        // ExtratorDeConteudo extractor = new ExtratorDeConteudoDaNasa();

        List<Conteudo> contents = extractor.extractorOfContent(json);

        // Manipulate Data and show them
        GeradoraDeStickers generator = new GeradoraDeStickers();
        for (int i=0; i<5;i++) {

            Conteudo content = contents.get(i);

            //String rateString = content.get("imDbRating");
            float rate = 10;
            String title = content.getTitle();
            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            try {
                generator.createStick(inputStream, rate, title.replace(":","")+".png");
                System.out.println("\u001b[3mTitle:\u001b[m\u001b[32;1m "+title+"\u001b[m");
            } catch (Exception e) {
                System.out.println("\u001b[3mTitle:\u001b[m\u001b[32;0m "+title+"\u001b[31;1m| Invalid URL\u001b[m");
                System.out.println("\u001b[3mImage: \u001b[m\u001b[34;4m"+inputStream+"\u001b[m");

            }
        }
    }
}
