import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeStickers {

    public void createStick(InputStream inputStream, String nomeArquivo) throws Exception {
        
        // read image
        //InputStream inputStream = new FileInputStream(new File("entry/filme.jpg")); // Local File
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // create new image without background
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 150;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy original image to a new image (memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // config font
        //
        try {
            Font font = 
                Font.createFont(Font.TRUETYPE_FONT, new File("properties/COMIC.TTF"));
            Font bold = font.deriveFont(Font.BOLD, 82);
            graphics.setColor(Color.CYAN);
            graphics.setFont(bold);
        } catch (Exception e) {
            Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 82);
            graphics.setColor(Color.CYAN);
            graphics.setFont(font);
        }

        // write a phrase in the new image
        graphics.drawString("Approved", (width/4)-20, height+100);

        // write new image in a archive
        var StringPath = "output/";
        File folder = new File(StringPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        ImageIO.write(newImage, "png", new File("output/"+nomeArquivo));
    }
    
}
