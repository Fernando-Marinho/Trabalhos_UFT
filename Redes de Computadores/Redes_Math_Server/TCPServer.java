import java.io.*;
import java.net.*;

class TCPServer {
	public static void main(String argv[]) throws Exception {
		
		ServerSocket socketRecepcao = null;
		
			socketRecepcao = new ServerSocket(6789);
			socketRecepcao.setReuseAddress(true);
			
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
			BufferedReader doServidor = null;
			
			try {
				paraCliente = new PrintWriter(clientSocket.getOutputStream(),true);
				doServidor = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
			
				String line;
				
				while((line = doServidor.readLine()) != null) {
					
					String[] op = line.split(" ");
					int var1 = 0;
					int var2 = 0;
					double resultado = 0;
					
					switch(op[1].charAt(0)) {
					case '+':
						var1 = Integer.parseInt(op[0]);
						var2 = Integer.parseInt(op[2]);
						resultado = var1 + var2;
						break;
					case '-':
						var1 = Integer.parseInt(op[0]);
						var2 = Integer.parseInt(op[2]);
						resultado = var1 - var2;
						break;
					case '*':
						var1 = Integer.parseInt(op[0]);
						var2 = Integer.parseInt(op[2]);
						resultado = var1 * var2;
						break;
					case '/':
						var1 = Integer.parseInt(op[0]);
						var2 = Integer.parseInt(op[2]);
						resultado = var1 / var2;
						break;			
					case 'r':
						resultado = Math.sqrt(Integer.parseInt(op[0]));
						break;
					case 's':
						resultado = Math.sin(Integer.parseInt(op[0]));
						break;
					case 'c':
						resultado = Math.cos(Integer.parseInt(op[0]));
						break;
					}
					
					System.out.println("Enviado do Cliente: "+line);
					paraCliente.println(resultado);
				}
			}catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(paraCliente != null) {
						paraCliente.close();
					}
					if(doServidor != null)
						doServidor.close();
					clientSocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
