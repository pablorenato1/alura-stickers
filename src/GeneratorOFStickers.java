import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeneratorOFStickers {

    public void createStick(InputStream inputStream, String nomeArquivo) throws Exception {
        
        // read image
        BufferedImage originalImage = ImageIO.read(inputStream);

        // create new image without background
        int width = (int) (originalImage.getWidth()* 0.85);
        int height = (int) (originalImage.getHeight() * 0.85);
        int newHeight = (int) ((height * 0.04) + height);
        int fontSize = (int) ((newHeight - height) * 0.65);
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
        graphics.setColor(Color.GREEN);
        graphics.drawString("TOPZEIRA",(int) ((width/7)*3), (int) (newHeight-(newHeight * 0.015)));

        // write new image in a archive
        var StringPath = "output/";
        File folder = new File(StringPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        ImageIO.write(newImage, "png", new File("output/"+nomeArquivo));
    }
    
}
