import java.io.Serializable;
import java.util.Scanner;

public class Quadrilatero implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double lado1, lado2, lado3, lado4;
	private String tipo = "Nao definido";
	
	public Quadrilatero(double  lado1, double lado2, double lado3, double lado4) {
		this.lado1 = lado1;
		this.lado2 = lado2;
		this.lado3 = lado3;
		this.lado4 = lado4;
	}
	
	public void ledados() {
		Scanner scanner = new Scanner(System.in);
		
		double lado;
		
		System.out.printf("Digite o valor do lado1: ");
		lado = scanner.nextDouble();
		this.setLado1(lado);
		
		System.out.printf("Digite o valor do lado2: ");
		lado = scanner.nextDouble();
		this.setLado2(lado);
		
		System.out.printf("Digite o valor do lado3: ");
		lado = scanner.nextDouble();
		this.setLado3(lado);
		
		System.out.printf("Digite o valor do lado4: ");
		lado = scanner.nextDouble();
		this.setLado4(lado);
				
		scanner.close();
		
	}
	
	public void indicatipoquadrilatero() {
		if(this.getLado1() == this.getLado2() && this.getLado2() == this.getLado3() && this.getLado3() == this.getLado4()) {
			this.setTipo("Quadrado");
		}else if(this.getLado1() == this.getLado3() && this.getLado2() == this.getLado4()) {
			this.setTipo("Retangulo");
		}else {
			this.setTipo("Quadrilatero");
		}
	}
	
	public void mostradados() {
		System.out.println(
				"Lado1: " + this.getLado1() + "\n" +
				"Lado2: " + this.getLado2() + "\n" + 
				"Lado3: " + this.getLado3() + "\n" +		
				"Lado4: " + this.getLado4() + "\n" + 
				"Tipo: " + this.getTipo() + "\n"
		);
	}
	
	public double getLado1() {
		return lado1;
	}

	public void setLado1(double lado1) {
		this.lado1 = lado1;
	}

	public double getLado2() {
		return lado2;
	}

	public void setLado2(double lado2) {
		this.lado2 = lado2;
	}

	public double getLado3() {
		return lado3;
	}

	public void setLado3(double lado3) {
		this.lado3 = lado3;
	}

	public double getLado4() {
		return lado4;
	}

	public void setLado4(double lado4) {
		this.lado4 = lado4;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
