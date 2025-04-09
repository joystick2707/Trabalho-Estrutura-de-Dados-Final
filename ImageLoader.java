import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageLoader {

    // Método estático para carregar uma imagem e convertê-la para o formato RGB
    public static BufferedImage loadImage(String path) {
        try {
            BufferedImage original = ImageIO.read(new File(path));

            // Cria uma nova imagem no formato RGB 
            BufferedImage rgb = new BufferedImage(
                    original.getWidth(),
                    original.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            // Cria um objeto Graphics2D para desenhar na nova imagem
            Graphics2D g = rgb.createGraphics();

            // Desenha a imagem original na nova imagem RGB
            g.drawImage(original, 0, 0, Color.WHITE, null);
            g.dispose();

            return rgb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
