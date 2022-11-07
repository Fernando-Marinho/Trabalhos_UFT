import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Histograma {
	
	public static BufferedImage NovaImagem(BufferedImage imagem, int[][] PixelsSaida) throws IOException {
			
	    BufferedImage saida = new BufferedImage(imagem.getHeight(), imagem.getWidth(), BufferedImage.TYPE_4BYTE_ABGR);
	    
	    for(int i=0; i<PixelsSaida.length; i++) {
	        for(int j=0; j<PixelsSaida.length; j++) {
	            int a = PixelsSaida[i][j];
	            Color newColor = new java.awt.Color(a,a,a,a);
	            saida.setRGB(i,j,newColor.getRGB());
	        }
	    }
    
    return saida;
}

	public static void main(String[] args) throws IOException {
		
		BufferedImage imagem = ImageIO.read(new File("GrayScale.png"));
	    BufferedImage saida = new BufferedImage(imagem.getHeight(), imagem.getWidth(), BufferedImage.TYPE_4BYTE_ABGR);
		int[][] pixels = new int[imagem.getHeight()][imagem.getWidth()];
		int[][] pixelsSaida = new int[imagem.getHeight()][imagem.getWidth()];
		int[] nk = new int[256]; // numero de pixels de cada nivel de cinza
		int n=0; //numero total de pixels
		float[] pr = new float[256]; //probabilidade do nivel de cinza
		float[] freq = new float[256]; //frequencia
		float[] eq = new float[256]; //equivalencias
		int[] rk = new int[256]; //equivalencias arredondadas
		
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				pixels[i][j] = imagem.getRGB(i, j);
				
				int alpha = (pixels[i][j] & 0xFF000000) >> 24;
		        int red = (pixels[i][j] & 0x00FF0000) >> 16;
		        int green = (pixels[i][j] & 0x0000FF00) >> 8;
		        int blue = (pixels[i][j] & 0x000000FF) >> 0;
		        
		        pixels[i][j] = (alpha+red+green+blue)/4;
		        
		        nk[pixels[i][j]]++;
				
			}
		}
		
		for (int i = 0; i < nk.length; i++) {
			n+=nk[i];
		}
				
		for (int i = 0; i < nk.length; i++) {
			pr[i]=(float)nk[i]/n;
		}
		
		freq[0] = pr[0];
		
		for (int i = 1; i < freq.length; i++) {
			freq[i] = freq[i-1]+pr[i];
		}
				
		eq[0]=255*pr[0];

		for (int i = 1; i < eq.length; i++) {
			eq[i]=(255*pr[i])+eq[i-1];
		}
		
		for (int i = 0; i < rk.length; i++) {
			rk[i]=Math.round(eq[i]);
		}
		
		
		for (int i = 0; i < pixelsSaida.length; i++) {
			for (int j = 0; j < pixelsSaida.length; j++) {
				pixelsSaida[i][j] = rk[(pixels[i][j])];
			}
		}
		
		saida = NovaImagem(imagem, pixelsSaida);
	    File output = new File("Saida.png");
	    ImageIO.write(saida, "png", output);
		
	}

}
