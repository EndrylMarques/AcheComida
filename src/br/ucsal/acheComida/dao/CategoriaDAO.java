package br.ucsal.acheComida.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.acheComida.model.Categoria;
import br.ucsal.acheComida.util.Conexao;

public class CategoriaDAO {

	private Conexao conexao;

	public CategoriaDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<Categoria> listar() {

		Statement stmt;
		List<Categoria> categorias = new ArrayList<>();

		try {

			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id, descricao from categoria;");
			while (rs.next()) {
				Categoria c = new Categoria();
				c.setId(rs.getInt("id"));
				c.setDescricao(rs.getString("descricao"));
				categorias.add(c);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;
	}

}
