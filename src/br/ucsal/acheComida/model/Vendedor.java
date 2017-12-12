package br.ucsal.acheComida.model;

public class Vendedor extends Usuario {

	private Produto produto;

	
	
	public Vendedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vendedor(String nome, String email, String senha, String telefone, Produto produto) {
		super(nome, email, senha, telefone);
		this.produto = produto;
		// TODO Auto-generated constructor stub
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
