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
		CategoriaDAO daoCategoria = new CategoriaDAO();
		Statement stmt;
		List<Produto> produtos = new ArrayList<>();
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id, descricao, categoria_id, valor from produtos;");
			while (rs.next()) {
				Produto p = new Produto();
				int categoriaId = Integer.valueOf(rs.getString("categoria_id"));
				p.setId(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));

				Categoria categoria = new Categoria();
				categoria = daoCategoria.getByID(categoriaId);
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
			// System.out.println("inserir");
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into produtos (descricao, categoria_id, valor) values (?,?,?);");
			ps.setString(1, produto.getDescricao());
			ps.setInt(2, produto.getCategoria().getId());
			ps.setDouble(3, produto.getValor());
			// System.out.println("d: "+produto.getDescricao());
			// System.out.println("c: "+produto.getCategoria().toString());
			// System.out.println("v: "+produto.getValor());
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
					.prepareStatement("select id, descricao, categoria_id, valor from produtos where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));

				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("categoria_id"));

				produto.setCategoria(categoria);
				produto.setValor(rs.getDouble("valor"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}

	public void atualizar(Produto produto) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update produtos set descricao = ?, categoria_id =?, valor=? where id =?;");
			ps.setString(1, produto.getDescricao());
			ps.setInt(2, produto.getCategoria().getId());
			ps.setDouble(3, produto.getValor());
			ps.setInt(4, produto.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Produto> organizar() {

		Statement stmt;
		List<Produto> produtos = new ArrayList<>();
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select descricao, valor from produtos order by valor;");
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));

				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("categoria_id"));
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

	public void update(Produto produto) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update produtos set descricao=?,categoria_id = ?, valor = ? where id =?;");
			ps.setString(1, produto.getDescricao());
			ps.setInt(2, produto.getCategoria().getId());
			ps.setDouble(3, produto.getValor());
			ps.setInt(4, produto.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	public void delete(int id) {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from produtos where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
