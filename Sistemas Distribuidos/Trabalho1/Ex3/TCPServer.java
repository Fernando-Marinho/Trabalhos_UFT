import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class TCPServer {
	public static void main(String argv[]) throws Exception {
		
		ServerSocket socketRecepcao = new ServerSocket(6789);
		socketRecepcao.setReuseAddress(true);

		System.out.println("Servidor Iniciado!");

		
		while (true) {
			Socket socketConexao = socketRecepcao.accept();
			System.out.println("Novo Cliente!" 	+ socketConexao.getInetAddress().getHostAddress());
			RecebeCliente clientSock = new RecebeCliente(socketConexao);
			
			new Thread(clientSock).start();
			
		}
	}
	
	static List<Produto> produtos = new ArrayList<Produto>();
	
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
			
				String line;
					
				while((line = doCliente.readLine()) != null) {
					
					String produto = line.split(" ")[0];
					int qtdProduto = Integer.parseInt(line.split(" ")[1]);
					
					Produto novoProduto = new Produto(produto, 0);
					boolean aux = false;
					String result = "";
					
					for (int i = 0; i < produtos.size(); i++) {
						if(produtos.get(i).getNome().equals(produto)) {
							aux = true;
							result = produtos.get(i).updateQtd(qtdProduto);
						}		
					}
					
					if(!aux) {
						result = novoProduto.updateQtd(qtdProduto);
						produtos.add(novoProduto);
					}
					
					paraCliente.println(result);
					
				}
				
			}catch(IOException e) {
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
