import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

	public static void main(String[] args) throws Exception{
		
		ServerSocket socketRecepcao  = new ServerSocket(6789);
		socketRecepcao.setReuseAddress(true);
		
		System.out.println("Servidor Iniciado!");
		
		while (true) {
			Socket socketConexao = socketRecepcao.accept();
			System.out.println("Novo Cliente!" 	+ socketConexao.getInetAddress().getHostAddress());
			RecebeCliente clientSock = new RecebeCliente(socketConexao);
			
			new Thread(clientSock).start();
			
		}
	}
	
	private static class RecebeCliente implements Runnable{
		private Socket clientSocket;
		
		public RecebeCliente(Socket socket) {
			this.clientSocket = new Socket();
			this.clientSocket = socket;
		}
		
		@Override
		public void run() {
			PrintWriter paraCliente = null;
			BufferedReader doCliente = null;
			
			try {
				paraCliente = new PrintWriter(clientSocket.getOutputStream(),true);
				doCliente = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

				WebCrawler crawler = new WebCrawler();
				String[] limite = doCliente.readLine().split(" ");
				int max_links = Integer.parseInt(limite[0]);
				int max_threads = Integer.parseInt(limite[1]);
				
			    crawler.crawl(max_links, max_threads);

				Thread.sleep(50);
			    
				paraCliente.println("Crawler iniciado, verifique os arquivos!");
				
			}catch(IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(paraCliente != null) {
						paraCliente.close();
					}
					if(doCliente != null)
						doCliente.close();
					clientSocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}


