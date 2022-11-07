import java.io.*;
import java.net.*;

class TCPServer {
	public static void main(String argv[]) throws Exception {

		ServerSocket socketRecepcao = new ServerSocket(6789);
		socketRecepcao.setReuseAddress(true);

		System.out.println("Servidor Iniciado!");

		while (true) {
			Socket socketConexao = socketRecepcao.accept();
			System.out.println("Novo Cliente!" + socketConexao.getInetAddress().getHostAddress());
			RecebeCliente clientSock = new RecebeCliente(socketConexao);

			new Thread(clientSock).start();
		}
	}

	private static class RecebeCliente implements Runnable {
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
				paraCliente = new PrintWriter(clientSocket.getOutputStream(), true);
				doCliente = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String line;

				while ((line = doCliente.readLine()) != null) {
					String numerosString[] = line.split(" ");

					int numeros[] = new int[3];

					int maior = 0;

					for (int i = 0; i < 3; i++) {
						numeros[i] = Integer.parseInt(numerosString[i]);

						if (numeros[i] > maior) {
							maior = numeros[i];
						}
					}

					if (numeros[0] < 0) {
						paraCliente.println("Conexão encerrada!");
						break;
					}

					System.out.println("Enviado do Cliente: " + line);
					paraCliente.println("Maior numero: " + maior);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (paraCliente != null) {
						paraCliente.close();
					}
					if (doCliente != null)
						doCliente.close();
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
