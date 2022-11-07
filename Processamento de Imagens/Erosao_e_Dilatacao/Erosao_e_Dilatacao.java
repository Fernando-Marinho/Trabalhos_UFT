import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Arrays;

public class Erosao_e_Dilatacao {
	
	static int largura;
	static int altura;
	
	public static void width_heigth(BufferedImage imagem) {
		largura=imagem.getWidth();
		altura=imagem.getHeight();
	}
	
	public static int[][] GeraMatriz(int[][] pixels, BufferedImage imagem){
		
		for (int i = 0; i < imagem.getWidth(); i++) {
			for (int j = 0; j < imagem.getHeight(); j++) {
				pixels[i][j] = imagem.getRGB(i, j);
				
		        int red = (pixels[i][j] & 0x00FF0000) >> 16;
		        int green = (pixels[i][j] & 0x0000FF00) >> 8;
		        int blue = (pixels[i][j] & 0x000000FF) >> 0;
		        
		        pixels[i][j] = (red+green+blue)/3;
			}
		}
		
		return pixels;
	}
	
	public static int[][] preencheSaida(int[][] pixels, int[][] saida){
		
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				saida[i][j]=pixels[i][j];
			}
		}
		
		return saida;
	}

	public static int[][] dilatacao(int[][] pixels, int[][] saida){
		
		for (int i = 1; i < largura-1; i++) {
			for (int j = 1; j < altura-1; j++) {
				int num[] = {pixels[i-1][j],pixels[i][j-1],pixels[i][j],pixels[i][j+1],pixels[i+1][j]};				
				Arrays.sort(num);
				saida[i][j] = num[(num.length)-1];
			}
		}
		
		return saida;
	}
	
	public static int[][] erosao(int[][] pixels, int[][] saida){
		
		for (int i = 1; i < largura-1; i++) {
			for (int j = 1; j < altura-1; j++) {
				int num[] = {pixels[i-1][j],pixels[i+1][j],pixels[i][j-1],pixels[i][j+1],pixels[i][j]};
				Arrays.sort(num);
				saida[i][j] = num[0];
			}
		}
		
		return saida;
	}

	public static BufferedImage NovaImagem(int[][] PixelsSaida) throws IOException {
		
	    BufferedImage saida = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
	    
	    for(int i=0; i<largura; i++) {
	        for(int j=0; j<altura; j++) {
	            int a = PixelsSaida[i][j];
	            Color newColor = new java.awt.Color(a,a,a);
	            saida.setRGB(i,j,newColor.getRGB());
	        }
	    }
    
    return saida;
    
	}
	
	public static void main(String[] args) throws IOException {
		BufferedImage imagem = ImageIO.read(new File("Entrada.png"));
		BufferedImage imagem2 = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
		File output;
	    int pixels[][] = new int[imagem.getWidth()][imagem.getHeight()];
		int saida[][] = new int[imagem.getWidth()][imagem.getHeight()];

		width_heigth(imagem);
		GeraMatriz(pixels, imagem);
		
		preencheSaida(pixels, saida);
		saida=dilatacao(pixels, saida);
		imagem2=NovaImagem(saida);
		output = new File("dilatacao.png");
		ImageIO.write(imagem2, "png", output);
				
		preencheSaida(pixels, saida);
		saida=erosao(pixels, saida);
		imagem2=NovaImagem(saida);
		output = new File("erosao.png");
		ImageIO.write(imagem2, "png", output);
		
	}

}
