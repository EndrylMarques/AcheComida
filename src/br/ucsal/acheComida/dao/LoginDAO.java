package br.ucsal.acheComida.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucsal.acheComida.model.Vendedor;

public class LoginDAO {
	

	
	public static Vendedor checkUser(String email, String senha) throws ClassNotFoundException {
		
		Vendedor vendedor = new Vendedor();
		try {
			
			Class.forName("org.postgresql.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AcheComida", "postgres",
					"eddy010296");
			
			PreparedStatement ps =  (PreparedStatement) con.prepareStatement("select * from vendedores where email=? and senha=?");
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
