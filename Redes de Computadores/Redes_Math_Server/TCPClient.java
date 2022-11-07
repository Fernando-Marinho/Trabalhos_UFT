import java.io.*;
import java.net.*;
import java.util.Scanner;


class TCPClient {
	public static void main(String argv[]) throws Exception {
		
		String ipv4 = "10.90.8.175";
		Socket socketCliente = new Socket(ipv4, 6789);
		
		PrintWriter paraServidor = new PrintWriter(socketCliente.getOutputStream(), true);
		BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
		Scanner scanner = new Scanner (System.in);
		String line = null;
		
		while(!"exist".equalsIgnoreCase(line)) {
			System.out.println("Digite uma entrada no formato: numero operador numero");
			line = scanner.nextLine();
			paraServidor.println(line);
			paraServidor.flush();
			System.out.println("Do Servidor: " + doServidor.readLine());
		}
		scanner.close();
		
		socketCliente.close();
	}
}
