import javax.swing.*;
import java.awt.image.BufferedImage;

public class FloodFillPilha {

    // Método estático que aplica o flood fill a partir da posição (x, y)
    public static void floodFill(BufferedImage image, int x, int y, int novaCor, JLabel imageLabel) {
        int corOriginal = image.getRGB(x, y); 

        if (corOriginal == novaCor) return;

        int largura = image.getWidth();
        int altura = image.getHeight();

        Pilha pilha = new Pilha(); 
        pilha.insere(new Pixel(x, y)); 

        while (!pilha.estaVazia()) {
            Pixel p = pilha.remover(); 
            if (p == null) continue;

            int px = p.x, py = p.y;

            // Verifica se o pixel está dentro dos limites da imagem
            if (px < 0 || py < 0 || px >= largura || py >= altura) continue;

            if (image.getRGB(px, py) != corOriginal) continue;

            // Pinta o pixel com a nova cor
            image.setRGB(px, py, novaCor);
            
            SwingUtilities.invokeLater(imageLabel::repaint);

            try {
                Thread.sleep(1); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Adiciona os vizinhos (acima, abaixo, direita e esquerda) na pilha
            pilha.insere(new Pixel(px + 1, py));
            pilha.insere(new Pixel(px - 1, py));
            pilha.insere(new Pixel(px, py + 1));
            pilha.insere(new Pixel(px, py - 1));
        }
    }
}
