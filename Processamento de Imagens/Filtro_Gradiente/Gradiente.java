import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gradiente {
	
	static int largura, altura;
	
	public static void width (BufferedImage imagem) {
		largura = imagem.getWidth();
	}
	
	public static void heigth (BufferedImage imagem) {
		altura = imagem.getHeight();
	}
	
	public static int[][] criaMascara1(int[][] mascara) {
		
		mascara[0][0] = -1; mascara[0][1] = -2; mascara[0][2] = -1;
		mascara[1][0] = 0;  mascara[1][1] = 0;  mascara[1][2] = 0;
		mascara[2][0] = 1;  mascara[2][1] = 2; mascara[2][2] = 1;
		
		return mascara;
	}
	
	public static int[][] criaMascara2(int[][] mascara) {
		
		mascara[0][0] = -1; mascara[0][1] = 0; mascara[0][2] = 1;
		mascara[1][0] = -2; mascara[1][1] = 0; mascara[1][2] = 2;
	    mascara[2][0] = -1; mascara[2][1] = 0; mascara[2][2] = 1;
	    
	    return mascara;
	}

	public static int[][] GeraMatriz(int[][] pixels, BufferedImage imagem){
		
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				pixels[i][j] = imagem.getRGB(i, j);
				
				int alpha = (pixels[i][j] & 0xFF000000) >> 24;
		        int red = (pixels[i][j] & 0x00FF0000) >> 16;
		        int green = (pixels[i][j] & 0x0000FF00) >> 8;
		        int blue = (pixels[i][j] & 0x000000FF) >> 0;
		        int soma = (alpha+red+green+blue)/4;
		        
		        pixels[i][j]=soma;

			}
		}
		
		return pixels;
	}
	
	public static int[][] aplicaFiltro(int[][] pixels, int[][]mascara, int[][] saida){
		
		for (int i = 1; i < largura-1; i++) {
			for (int j = 1; j < altura-1; j++) {
				
				int media = (pixels[i-1][j-1]*mascara[0][0]) + (pixels[i-1][j]*mascara[0][1]) + (pixels[i-1][j+1]*mascara[0][2])
						  + (pixels[i][j-1]*mascara[1][0]) + (pixels[i][j]*mascara[1][1]) + (pixels[i][j+1]*mascara[1][2]) 
						  + (pixels[i+1][j-1]*mascara[2][0]) + (pixels[i+1][j]*mascara[2][1]) + (pixels[i+1][j+1]*mascara[2][2]);
				
				if(media < 0) {
					saida[i][j] = 0;
				}else if (media>255){
					saida[i][j] = 255;
				}else {
					saida[i][j] = media;
				}
				
			}
		}
		
		return saida;
	}
	
	
	public static int[][] resultado(int[][] saida1, int[][] saida2){
		
		int resultado[][] = new int[largura][altura];
		
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				resultado[i][j] = (int) Math.sqrt((saida1[i][j]*saida1[i][j])+(saida2[i][j]*saida2[i][j]));
				
				if(resultado[i][j]>255) {
					resultado[i][j]=255;
				}
			}
		}
		return resultado;
	}
	
	public static BufferedImage NovaImagem(BufferedImage imagem, int[][] PixelsSaida) throws IOException {
		
	    BufferedImage saida = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    
	    for(int i=0; i < largura; i++) {
	        for(int j=0; j < altura; j++) {
	            int a = PixelsSaida[i][j];
	            Color newColor = new java.awt.Color(a,a,a,a);
	            saida.setRGB(i,j,newColor.getRGB());
	        }
	    }
    
	    return saida;
   }

	public static void main(String[] args) throws IOException {
		BufferedImage imagem = ImageIO.read(new File("Entrada.png"));
		
		BufferedImage novaImagem1 = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    BufferedImage novaImagem2 = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    BufferedImage imagemFinal = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    
	    int pixels[][] = new int[imagem.getWidth()][imagem.getHeight()];
	    int saida1[][] = new int[imagem.getWidth()][imagem.getHeight()];
	    int saida2[][] = new int[imagem.getWidth()][imagem.getHeight()];
	    int resultado[][] = new int[imagem.getWidth()][imagem.getHeight()];
		int mascara[][] = new int[3][3];
		File output;
		
		width(imagem);
		heigth(imagem);
		pixels = GeraMatriz(pixels, imagem);
		
		mascara=criaMascara1(mascara);
		saida1=aplicaFiltro(pixels, mascara, saida1);
	    novaImagem1 = NovaImagem(imagem, saida1);
	    output = new File("Gradiente1.png");
	    ImageIO.write(novaImagem1, "png", output);

	    mascara=criaMascara2(mascara);
		saida2=aplicaFiltro(pixels, mascara, saida2);
	    novaImagem2 = NovaImagem(imagem, saida2);
	    output = new File("Gradiente2.png");
	    ImageIO.write(novaImagem2, "png", output);
	    
	    resultado=resultado(saida1, saida2);
	    
	    imagemFinal = NovaImagem(imagem, resultado);
	    output = new File("Saida.png");
	    ImageIO.write(imagemFinal, "png", output);
	    
	}

}
