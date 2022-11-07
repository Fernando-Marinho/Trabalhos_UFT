
public class Produto {
	private String nome;
	private int qtd;
	
	public Produto(String nome, int qtd) {
		this.nome = nome;
		this.qtd = qtd;
	}
	
	public String updateQtd(int newQtd) {
		int qtd = this.getQtd();
		
		if(newQtd < 0) {
			if(qtd + newQtd < 0) {
				return "Nao e possível fazer a saida de estoque – quantidade menor que o valor desejado";
			}else {
				this.setQtd(qtd + newQtd);
				return ("estoque atualizado e quantidade de " + this.getQtd());
			}
		}
		else {
			this.setQtd(qtd + newQtd);
			return ("estoque atualizado e quantidade de " + this.getQtd());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	

}
