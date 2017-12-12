package br.ucsal.acheComida.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.acheComida.model.Categoria;
import br.ucsal.acheComida.model.Produto;
import br.ucsal.acheComida.util.Conexao;

public class ProdutoDAO {

	private Conexao conexao;

	public ProdutoDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<Produto> listar() {
		Statement stmt;
		List<Produto> produtos = new ArrayList<>();
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id, descricao, categoriaid, valor from produtos;");
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));

				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("categoriaid"));
				p.setCategoria(categoria);

				p.setValor(rs.getDouble("valor"));
				produtos.add(p);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	public void inserir(Produto produto) {
		try {
//			System.out.println("inserir");
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into produtos (descricao, categoriaid, valor) values (?,?,?);");
			ps.setString(1, produto.getDescricao());
			ps.setInt(2, produto.getCategoria().getId());
			ps.setDouble(3, produto.getValor());
//			System.out.println("d: "+produto.getDescricao());
//			System.out.println("c: "+produto.getCategoria().toString());
//			System.out.println("v: "+produto.getValor());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Produto getByID(int id) {
		Produto produto = null;
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id, descricao, categoriaid, valor from produtos where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));

				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("categoriaid"));

				produto.setCategoria(categoria);
				produto.setValor(rs.getDouble("valor"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}

}