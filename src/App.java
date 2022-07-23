import java.net.URI;
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
        // Show and manipulate data
        for (Map<String,String> filmes : listaDeFilmes) {
            System.out.println("\u001b[3mTitle:\u001b[m\u001b[32;1m "+filmes.get("title")+"\u001b[m");
            System.out.println("\u001b[3mImage: \u001b[m\u001b[34;4m"+filmes.get("image")+"\u001b[m");
            System.out.println("\u001b[3mRate:\u001b[m\u001b[38;5;214m "+filmes.get("imDbRating")+"\u001b[m");
        }
    }
}
