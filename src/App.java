import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        // Do a connection HTTP and get top 250 Best movies
        String url = ArquivoDePropriedades.readProperties("IMDB_MOVIES");
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> respose = client.send(request, BodyHandlers.ofString());
        String body = respose.body();
        
        // Filter the data from movies (Title, Rate, Poster)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Manipulate Data and show them
        GeradoraDeStickers generator = new GeradoraDeStickers();
        for (Map<String,String> filmes : listaDeFilmes) {
            String urlImagebrute = filmes.get("image");
            String rateString = filmes.get("imDbRating");
            float rate = Float.parseFloat(rateString);
            var urlImage = urlImagebrute.substring(0, urlImagebrute.length()-32) +".png";
            String title = filmes.get("title");
            
            try {
                String nomeArquivo = title + ".png";
                InputStream inputStream = new URL(urlImage).openStream();
                generator.createStick(inputStream, rate,nomeArquivo);
                System.out.println("\u001b[3mTitle:\u001b[m\u001b[32;1m "+title+"\u001b[m");
            } catch (Exception e) {
                System.out.println("\u001b[3mTitle:\u001b[m\u001b[32;0m "+title+"\u001b[31;1m| Invalid URL\u001b[m");
                System.out.println("\u001b[3mImage: \u001b[m\u001b[34;4m"+urlImagebrute+"\u001b[m");
            }
        }
    }
}
