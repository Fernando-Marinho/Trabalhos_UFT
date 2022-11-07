import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;


class TCPClient {
	public static void main(String argv[]) throws Exception {

		Socket socketCliente = new Socket("localhost", 6789);
		
		PrintWriter paraServidor = new PrintWriter(socketCliente.getOutputStream(), true);
		BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
		Scanner scanner = new Scanner (System.in);
				
		while(true) {
			String nome = "";
			int qtd = 0;

			System.out.print("Digite o nome do produto: ");
			nome = scanner.next();

			if(nome.equals("terminar")) {System.out.println("Programa Encerrado!"); break;}
		
			System.out.print("Digite a quantidade do produto: ");
			qtd = scanner.nextInt();

			
			paraServidor.println(nome+" "+Integer.toString(qtd));
			paraServidor.flush();
			System.out.println("Do Servidor: " + doServidor.readLine());
		}
		scanner.close();
		
		socketCliente.close();
	}
}
