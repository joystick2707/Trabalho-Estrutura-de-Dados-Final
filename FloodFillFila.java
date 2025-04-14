import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

// Classe que implementa o algoritmo Flood Fill utilizando uma fila (varredura por largura)
public class FloodFillFila {

    public static void floodFill(BufferedImage image, int x, int y, int novaCor, JLabel imageLabel) {
        // Obtém a cor original do pixel de partida
        int corOriginal = image.getRGB(x, y);
        
        if (corOriginal == novaCor) return;

        int largura = image.getWidth();
        int altura = image.getHeight();

        Fila fila = new Fila();
        fila.enfileirar(new Pixel(x, y));

        while (!fila.estaVazia()) {
            Pixel p = fila.desenfileirar();

            if (p != null) {
                int px = p.x;
                int py = p.y;

                // Verifica se o pixel está dentro dos limites da imagem e tem a cor original
                if (px >= 0 && py >= 0 && px < largura && py < altura && image.getRGB(px, py) == corOriginal) {
                    image.setRGB(px, py, novaCor);

                    // Enfileira os quatro vizinhos (cima, baixo, esquerda, direita)
                    fila.enfileirar(new Pixel(px + 1, py)); 
                    fila.enfileirar(new Pixel(px - 1, py)); 
                    fila.enfileirar(new Pixel(px, py + 1)); 
                    fila.enfileirar(new Pixel(px, py - 1)); 

                    SwingUtilities.invokeLater(imageLabel::repaint);

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Processando pixel: (" + px + ", " + py + ")");
                }
            }
        }
    }
}
