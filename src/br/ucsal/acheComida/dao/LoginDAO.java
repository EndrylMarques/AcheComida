package br.ucsal.acheComida.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucsal.acheComida.model.Vendedor;
import br.ucsal.acheComida.util.Conexao;

public class LoginDAO {
	private static  Conexao conexao;

	public LoginDAO() {
		this.conexao = Conexao.getConexao();
	}
	
	public static Vendedor checkUser(String email, String senha) {
		Vendedor vendedor = new Vendedor();
		try {
			PreparedStatement ps =  conexao.getConnection()
					.prepareStatement("select * from usuario where email=? and senha=?");
			ps.setString(1, email);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				vendedor = new Vendedor();
				vendedor.setId(rs.getInt("id"));
				vendedor.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendedor;
	}

}
