package br.ucsal.acheComida.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.acheComida.model.Categoria;
import br.ucsal.acheComida.model.Produto;
import br.ucsal.acheComida.model.Usuario;
import br.ucsal.acheComida.util.Conexao;

public class UsuarioDAO {

	private Conexao conexao;

	public UsuarioDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<Usuario> listarLazy() {
		Statement stmt;
		List<Usuario> usuarios = new ArrayList<>();
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id,nome,email,senha,telefone,produto_id from usuarios;");
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setTelefone(rs.getString("telefone"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				u.setProduto(produto);

				usuarios.add(u);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public List<Usuario> listar() {
		Statement stmt;
		List<Usuario> usuarios = new ArrayList<>();
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select usuario.id,nome,email,senha,telefone,produto_id, produtos.nome as nomeproduto from usuarios,produtos where usuarios.produto_id = produtos.id;");
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setTelefone(rs.getString("telefone"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				produto.setDescricao(rs.getString("descricao_produto"));

				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("categoria_id"));
				categoria.setDescricao(rs.getString("descricao_categoria"));

				produto.setCategoria(categoria);
				produto.setValor(rs.getDouble("valor"));
				u.setProduto(produto);

				usuarios.add(u);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public void inserir(Usuario usuario) {
		try {

			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"insert into usuarios (nome,email,senha,telefone,produto_id) values (?,?,?,?,?);");
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getTelefone());
			ps.setInt(5, usuario.getProduto().getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Usuario getByID(int id) {
		Usuario u = null;
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,nome,email,senha,telefone,produto_id from tipos where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setTelefone(rs.getString("telefone"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				u.setProduto(produto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public void delete(int id) {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from tipos where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Usuario usuario) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update tipos set nome=?,email = ?, senha = ?, telefone = ?, produto_id = ? where id =?;");
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(2, usuario.getSenha());
			ps.setString(2, usuario.getTelefone());
			ps.setInt(2, usuario.getProduto().getId());
			ps.setInt(3, usuario.getId());
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
