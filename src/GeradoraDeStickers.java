import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeStickers {

    public void createStick(InputStream inputStream, Float rate, String nomeArquivo) throws Exception {
        
        // read image
        //InputStream inputStream = new FileInputStream(new File("entry/filme.jpg")); // Local File
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // create new image without background
        int width = 750;//originalImage.getWidth();
        int height = 1200;//originalImage.getHeight();
        int newHeight = height+130;//(int) ((height * 0.04) + height);
        int fontSize = 64;//(int) ((newHeight - height) * 0.65);
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        // copy original image to a new image (memory)
        Graphics2D graphics = (Graphics2D) newImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, width, height, null);
        

        // config font
        try {
            Font font = 
                Font.createFont(Font.TRUETYPE_FONT, new File("properties/COMIC.TTF"));
            Font bold = font.deriveFont(Font.BOLD, fontSize);
            graphics.setColor(Color.CYAN);
            graphics.setFont(bold);
        } catch (Exception e) {
            Font font = new Font(Font.SANS_SERIF, Font.ITALIC, fontSize);
            graphics.setColor(Color.CYAN);
            graphics.setFont(font);
        }

        // write a phrase in the new image
        if (newHeight > 11000) {
            return;
        } else if (rate >= 8.8) {
            graphics.setColor(Color.GREEN);
            graphics.drawString("BOM DEMAIS"+" "+ rate, (int) (width/10), (int) (newHeight-50));
            
        } else if (rate >= 8.4 && rate < 8.8) {
            graphics.setColor(Color.YELLOW);
            graphics.drawString("TOPZEIRA"+" "+ rate, (int) ((width/10)*1.5), (int) (newHeight-50));
        } else {
            graphics.setColor(Color.RED);
            graphics.drawString("ESSE É BOM"+" "+ rate, (int) ((width/10)*1.3), (int) (newHeight-50));
        }
        graphics.dispose();
        

        // write new image in a archive
        var StringPath = "output/";
        File folder = new File(StringPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        ImageIO.write(newImage, "png", new File(StringPath+nomeArquivo));
    }
    
}
