package br.ucsal.acheComida.model;

public class Produto {

	private Integer id;
	private String descricao;
	private Categoria categoria;
	private Double valor;

	public Produto() {
		super();
	}

	public Produto(String descricao, Categoria categoria, Double valor) {
		this.descricao = descricao;
		this.categoria = categoria;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", categoria=" + categoria + ", valor=" + valor + "]";
	}
	
	

}
