import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ArquivoDePropriedades {
    
    public static String readProperties(String string) throws IOException {
        
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("./properties/config.properties");
        prop.load(file);
        var result = prop.getProperty(string);
        
        return result;
    }
}
