import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class readPropriedades {
    static int amount=5;
    static String dateStart;
    static String dateEnd;

    public String getAPIUrl(String APIName) throws IOException {

        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("./properties/config.properties");
        prop.load(file);
        String urlImage = prop.getProperty(APIName);

        return urlImage;
    }
}
