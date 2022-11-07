import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Adicao_Subtracao {
	
	public static BufferedImage Adicao_e_Subtracao(BufferedImage imagem, BufferedImage imagem2, int ctrl) {
		
	    BufferedImage entrada = imagem;
	    BufferedImage entrada2 = imagem2;
	    BufferedImage saida = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    
	    int width = entrada.getWidth();
	    int height = entrada.getHeight();
	    
	    int[] entradaPixels = entrada.getRGB(0, 0, width, height, null, 0, width);
	    int[] entrada2Pixels = entrada2.getRGB(0, 0, width, height, null, 0, width);
	    int[] saidaPixels = new int[entradaPixels.length];
	    
	    for (int i = 0; i < entradaPixels.length; i++) {
	        int alpha = (entradaPixels[i] & 0xFF000000) >> 24;
	        int red = (entradaPixels[i] & 0x00FF0000) >> 16;
	        int green = (entradaPixels[i] & 0x0000FF00) >> 8;
	        int blue = (entradaPixels[i] & 0x000000FF) >> 0;
	        
	        int alpha2 = (entrada2Pixels[i] & 0xFF000000) >> 24;
	        int red2 = (entrada2Pixels[i] & 0x00FF0000) >> 16;
	        int green2 = (entrada2Pixels[i] & 0x0000FF00) >> 8;
	        int blue2 = (entrada2Pixels[i] & 0x000000FF) >> 0;
	        
	        if(ctrl==0) {
	        	saidaPixels[i] = 
		        		  ((alpha & 0xFF) << 24) + ((alpha2 & 0xFF) << 24) 
		        		| ((red & 0xFF) << 16) + ((red2 & 0xFF) << 16)
		        		| ((green & 0xFF) << 8) + ((green2 & 0xFF) << 8)
		        		| (blue & 0xFF) + (blue2 & 0xFF);

	        	
	        }else {
	        	saidaPixels[i] = 
		        		  ((alpha & 0xFF) << 24) - ((alpha2 & 0xFF) << 24) 
		        		| ((red & 0xFF) << 16) - ((red2 & 0xFF) << 16)
		        		| ((green & 0xFF) << 8) - ((green2 & 0xFF) << 8)
		        		| (blue & 0xFF) - (blue2 & 0xFF);
	        } 
	    }
	    
	    saida.setRGB(0, 0, width, height, saidaPixels, 0, width);
	    return saida;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedImage imagem = ImageIO.read(new File("vermelho.png"));
		BufferedImage imagem2 = ImageIO.read(new File("verde.png"));
		
		int ctrl = 0;
		
		File adicao = new File("resultado_adicao.png");
		BufferedImage saida = Adicao_e_Subtracao(imagem, imagem2, ctrl);
	    ImageIO.write(saida, "png", adicao);
	    
	    ctrl = 1;
	    
	    File subtracao = new File("resultado_subtracao.png");
	    saida=Adicao_e_Subtracao(imagem, imagem2, ctrl);
	    ImageIO.write(saida, "png", subtracao);

	    
	}
	

}
