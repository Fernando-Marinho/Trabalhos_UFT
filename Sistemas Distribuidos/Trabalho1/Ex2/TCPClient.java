import java.io.*;
import java.net.*;


class TCPClient {
	public static void main(String argv[]) throws Exception {

		Socket socketCliente = new Socket("localhost", 6789);
		
		Quadrilatero quadrilatero = new Quadrilatero(0, 0, 0, 0);
		quadrilatero.ledados();
		
		ObjectOutputStream paraServidor = new ObjectOutputStream(socketCliente.getOutputStream());
		
		paraServidor.writeObject(quadrilatero);
		paraServidor.flush();
		
		ObjectInputStream doServidor = new ObjectInputStream(socketCliente.getInputStream());;
	
		Quadrilatero quadrilatero1 = (Quadrilatero) doServidor.readObject();
		quadrilatero1.mostradados();
			
		doServidor.close();
		
		socketCliente.close();
	}
}
