import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeneratorOfStickersRate {

    public void createStick(InputStream inputStream, String rateString, String nomeArquivo) throws Exception {
        float rate = Float.parseFloat(rateString);
        // read image
        BufferedImage originalImage = ImageIO.read(inputStream);

        // create new image without background
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = (int) ((height * 0.04) + height);
        int fontSize = (int) ((newHeight - height) * 0.65);
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        // copy original image to a new image (memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

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
            graphics.drawString("BOM DEMAIS"+" "+ rate, (int) ((width/7)*3), (int) (newHeight-(newHeight * 0.015)));
            
        } else if (rate >= 8.4 && rate < 8.8) {
            graphics.setColor(Color.YELLOW);
            graphics.drawString("TOPZEIRA"+" "+ rate, (int) ((width/7)*3), (int) (newHeight-(newHeight * 0.015)));
        } else {
            graphics.setColor(Color.RED);
            graphics.drawString("ESSE É BOM"+" "+ rate, (int) ((width/7)*3), (int) (newHeight-(newHeight * 0.015)));
        }

        // write new image in a archive
        var StringPath = "output/";
        File folder = new File(StringPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        ImageIO.write(newImage, "png", new File("output/"+nomeArquivo));
    }
    
}
