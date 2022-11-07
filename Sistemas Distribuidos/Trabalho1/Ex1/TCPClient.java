import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
	public static void main(String argv[]) throws Exception {

		Socket socketCliente = new Socket("localhost", 6789);

		PrintWriter paraServidor = new PrintWriter(socketCliente.getOutputStream(), true);
		BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
		Scanner scanner = new Scanner(System.in);
		int[] numeros = new int[3];

		String numerosString = "";

		for (int i = 0; i < 3; i++) {
			System.out.printf("Digite o %d numero:", i + 1);
			numeros[i] = scanner.nextInt();
			numerosString += Integer.toString(numeros[i]) + " ";
		}

		paraServidor.println(numerosString);
		paraServidor.flush();
		System.out.println(doServidor.readLine());

		scanner.close();

		socketCliente.close();
	}
}
