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

			ObjectInputStream doCliente = null;
			ObjectOutputStream paraCliente = null;

			try {

				doCliente = new ObjectInputStream(clientSocket.getInputStream());

				try {
					Quadrilatero quadrilatero = (Quadrilatero) doCliente.readObject();
					quadrilatero.indicatipoquadrilatero();

					paraCliente = new ObjectOutputStream(clientSocket.getOutputStream());
					paraCliente.writeObject(quadrilatero);
					paraCliente.flush();

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
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
