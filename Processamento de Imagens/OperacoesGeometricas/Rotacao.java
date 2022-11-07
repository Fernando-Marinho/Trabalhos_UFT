import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rotacao {
	
	public static BufferedImage rotate(BufferedImage imagem, double angulo) {

	    int w = imagem.getWidth();    
	    int h = imagem.getHeight();

	    BufferedImage rotated = new BufferedImage(w, h, imagem.getType());  
	    Graphics2D graphic = rotated.createGraphics();
	    graphic.rotate(Math.toRadians(angulo), w/2, h/2);
	    graphic.drawImage(imagem, null, 0, 0);
	    graphic.dispose();
	    return rotated;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedImage imagem = ImageIO.read(new File("Entrada.png"));
		
		File imagem2 = new File("Saida.png");
		BufferedImage saida = rotate(imagem, 180);
	    ImageIO.write(saida, "png", imagem2);
		
	}

}
