package br.ucsal.acheComida.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.acheComida.model.Categoria;
import br.ucsal.acheComida.model.Produto;
import br.ucsal.acheComida.model.Vendedor;
import br.ucsal.acheComida.util.Conexao;

public class VendedorDAO {

	private Conexao conexao;

	public VendedorDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<Vendedor> listarLazy() {
		Statement stmt;
		List<Vendedor> vendedores = new ArrayList<>();
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id,nome,email,senha,telefone,produto_id from vendedores;");
			while (rs.next()) {
				Vendedor v = new Vendedor();
				v.setId(rs.getInt("id"));
				v.setNome(rs.getString("nome"));
				v.setEmail(rs.getString("email"));
				v.setSenha(rs.getString("senha"));
				v.setTelefone(rs.getString("telefone"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				v.setProduto(produto);

				vendedores.add(v);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vendedores;
	}

	public List<Vendedor> listar() {
		Statement stmt;
		List<Vendedor> vendedores = new ArrayList<>();
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"select vendedores.id,nome,email,senha,telefone,produto_id,produtos.valor, produtos.descricao from vendedores inner join produtos on vendedores.produto_id = produtos.id;");
			while (rs.next()) {
				Vendedor v = new Vendedor();
				v.setId(rs.getInt("id"));
				v.setNome(rs.getString("nome"));
				v.setEmail(rs.getString("email"));
				v.setSenha(rs.getString("senha"));
				v.setTelefone(rs.getString("telefone"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(Double.parseDouble(rs.getString("valor")));
				v.setProduto(produto);

//				Categoria categoria = new Categoria();
//				categoria.setId(rs.getInt("categoria_id"));
//				categoria.setDescricao(rs.getString("descricao_categoria"));
//
//				produto.setCategoria(categoria);
//				produto.setValor(rs.getDouble("valor"));
//				v.setProduto(produto);

				vendedores.add(v);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vendedores;
	}

	public void inserir(Vendedor vendedor) {
		try {

			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"insert into vendedores (nome,email,senha,telefone,produto_id) values (?,?,?,?,?);");
			ps.setString(1, vendedor.getNome());
			ps.setString(2, vendedor.getEmail());
			ps.setString(3, vendedor.getSenha());
			ps.setString(4, vendedor.getTelefone());
			ps.setInt(5, vendedor.getProduto().getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Vendedor getByID(int id) {
		Vendedor v = null;
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,nome,email,senha,telefone,produto_id from vendedores where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				v = new Vendedor();
				v.setId(rs.getInt("id"));
				v.setNome(rs.getString("nome"));
				v.setEmail(rs.getString("email"));
				v.setSenha(rs.getString("senha"));
				v.setTelefone(rs.getString("telefone"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				v.setProduto(produto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	public void delete(int id) {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from vendedores where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Vendedor vendedor) {
		try {

			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"update vendedores set nome=?,email = ?, senha = ?, telefone = ?, produto_id = ? where id =?;");
			ps.setString(1, vendedor.getNome());
			ps.setString(2, vendedor.getEmail());
			ps.setString(2, vendedor.getSenha());
			ps.setString(2, vendedor.getTelefone());
			ps.setInt(2, vendedor.getProduto().getId());
			ps.setInt(3, vendedor.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {
		conexao.closeConnection();
	}
}
