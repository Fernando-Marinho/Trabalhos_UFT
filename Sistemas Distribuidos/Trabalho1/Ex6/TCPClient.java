import java.io.*;
import java.net.*;
import java.util.Scanner;


class TCPClient {
	public static void main(String argv[]) throws Exception {

		Socket socketCliente = new Socket("localhost", 6789);
		
		PrintWriter paraServidor = new PrintWriter(socketCliente.getOutputStream(), true);
		BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
		Scanner scanner = new Scanner (System.in);
		
		int n = 0;
		while(n==0) {
			System.out.print("Digite o limite de links: ");
			int limite = scanner.nextInt();
			System.out.print("Digite o limite de threads: ");
			int max_thread = scanner.nextInt();
			paraServidor.println(Integer.toString(limite)+" "+Integer.toString(max_thread));
			paraServidor.flush();
			System.out.println("Do Servidor: " + doServidor.readLine());
			n=1;
		}
		scanner.close();
		
		socketCliente.close();
	}
}
