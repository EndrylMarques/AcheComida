package br.ucsal.acheComida.dao;

import java.sql.PreparedStatement;
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
			ResultSet rs = stmt.executeQuery("select id, descricao from categorias;");
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

	public void inserir(Categoria categoria) {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into categorias (descricao) values (?);");
			ps.setString(1, categoria.getDescricao());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Categoria getByID(int id) {
		Categoria cat = null;
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id, descricao from categorias where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cat = new Categoria();
				cat.setId(rs.getInt("id"));
				cat.setDescricao(rs.getString("descricao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}

	public void close() {
		conexao.closeConnection();
	}

	public void excluir(int id) {
		// TODO Auto-generated method stub
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from categorias where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizar(Categoria categoria) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update categorias set descricao = ? where id =?;");
			ps.setString(1, categoria.getDescricao());
			ps.setInt(2, categoria.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
