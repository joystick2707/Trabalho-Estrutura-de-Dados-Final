import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// Enum para escolher o tipo de preenchimento
enum ModoPreenchimento {
    PILHA, FILA
}

public class FloodFillGUI extends JFrame {
    private BufferedImage image;
    private JLabel imageLabel;
    private ModoPreenchimento modo;

    public FloodFillGUI(String imagePath, ModoPreenchimento modo) {
        this.modo = modo;

        setTitle("Flood Fill - " + modo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        image = ImageLoader.loadImage(imagePath);
        if (image == null) {
            System.out.println("Erro ao carregar a imagem.");
            System.exit(1);
        }

        imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel, BorderLayout.CENTER);

        setSize(image.getWidth(), image.getHeight());
        setLocationRelativeTo(null);
        setVisible(true);

        iniciarFloodFill(Color.BLUE.getRGB());
    }

    private void iniciarFloodFill(int novaCor) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++) {
                        if (ehPreto(image.getRGB(x, y))) {
                            if (modo == ModoPreenchimento.FILA) {
                                FloodFillFila.floodFill(image, x, y, novaCor, imageLabel);
                            } else {
                                FloodFillPilha.floodFill(image, x, y, novaCor, imageLabel);
                            }
                        }
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                ImageSaver.saveImage(image, "output.png");
                System.out.println("Imagem salva como output.png");
            }
        };

        worker.execute();
    }

    private boolean ehPreto(int cor) {
        int a = (cor >> 24) & 0xFF;
        int r = (cor >> 16) & 0xFF;
        int g = (cor >> 8) & 0xFF;
        int b = cor & 0xFF;

        return a > 0 && r < 100 && g < 100 && b < 100;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Caminho da imagem
            String caminhoImagem = "/home/bryan/Downloads/bryan.portifolio-main/Pilha/images/arvores.png";

            //Tirar os comentarios do modo que voce quer pintar
            // ModoPreenchimento modo = ModoPreenchimento.PILHA;
             ModoPreenchimento modo = ModoPreenchimento.FILA;

            new FloodFillGUI(caminhoImagem, modo);
        });
    }
}
