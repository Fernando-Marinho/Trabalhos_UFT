import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Filtro_da_Media {
	
	static int largura, altura;
	
	public static void width (BufferedImage imagem) {
		largura = imagem.getWidth();
	}
	
	public static void heigth (BufferedImage imagem) {
		altura = imagem.getHeight();
	}
	
	public static int[][] GeraMatriz(int[][] pixels, BufferedImage imagem){
		
		for (int i = 0; i < imagem.getWidth(); i++) {
			for (int j = 0; j < imagem.getHeight(); j++) {
				pixels[i][j] = imagem.getRGB(i, j);
				
				int alpha = (pixels[i][j] & 0xFF000000) >> 24;
		        int red = (pixels[i][j] & 0x00FF0000) >> 16;
		        int green = (pixels[i][j] & 0x0000FF00) >> 8;
		        int blue = (pixels[i][j] & 0x000000FF) >> 0;
		        
		        pixels[i][j] = (alpha+red+green+blue)/4;
				
			}
		}
		
		return pixels;
	}
	
	public static int[][] criaMascara(int[][] mascara) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mascara[i][j]=1;
			}
		}
		return mascara;
	}
	
	public static int[][] aplicaFiltro(int[][] pixels, int[][]mascara, int[][] saida){
	
		for (int i = 1; i < largura-1; i++) {
			for (int j = 1; j < altura-1; j++) {
				
				int media = pixels[i-1][j-1]*mascara[0][0] + pixels[i-1][j]*mascara[0][1] + pixels[i-1][j+1]*mascara[0][2]
						  + pixels[i][j-1]*mascara[1][0] + pixels[i][j]*mascara[1][1] + pixels[i][j+1]*mascara[1][2] 
						  + pixels[i+1][j-1]*mascara[2][0] + pixels[i+1][j]*mascara[2][1] + pixels[i+1][j+1]*mascara[2][2];
				media = Math.round(media/9);
								
				if(media <= 0) {
					saida[i][j] = 0;
				}else if(media >255){
					saida[i][j] = 255;
				}else {
					saida[i][j] = media;
				}				
			}
		}
		
		return saida;
	}
	
	public static BufferedImage NovaImagem(BufferedImage imagem, int[][] PixelsSaida) throws IOException {
		
	    BufferedImage saida = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    
	    for(int i=0; i<largura; i++) {
	        for(int j=0; j<altura; j++) {
	            int a = PixelsSaida[i][j];
	            Color newColor = new java.awt.Color(a,a,a,a);
	            saida.setRGB(i,j,newColor.getRGB());
	        }
	    }
    
    return saida;
  }

	public static void main(String[] args) throws IOException {
		
		BufferedImage imagem = ImageIO.read(new File("Entrada.png"));
	    BufferedImage novaImagem = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		
	    int pixels[][] = new int[imagem.getWidth()][imagem.getHeight()];
		int saida[][] = new int[imagem.getWidth()][imagem.getHeight()];
		int mascara[][] = new int[3][3];
		
		width(imagem);
		heigth(imagem);
		
		pixels=GeraMatriz(pixels, imagem);
		mascara=criaMascara(mascara);
		saida=aplicaFiltro(pixels, mascara, saida);
		
		novaImagem = NovaImagem(imagem, saida);
	    File output = new File("Saida.png");
	    ImageIO.write(novaImagem, "png", output);
		
	}

}
