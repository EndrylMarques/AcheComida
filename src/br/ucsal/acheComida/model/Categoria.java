package br.ucsal.acheComida.model;

public class Categoria {

	private Integer id;
	private String descricao;
	
	public Categoria(){
		
	}

	public Categoria(String descricao) {
		this.descricao = descricao;
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

}
