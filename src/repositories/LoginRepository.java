package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Usuario;

public class LoginRepository {
	
	public boolean login(Usuario usuario) {
		String sql = "SELECT username_usuario FROM usuario WHERE username_usuario = ? AND password_usuario = ?";
		boolean ret = false;
		PreparedStatement preparedStatement= ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPassword());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			ret = resultSet.next();
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fazer login");
		}
		
		return ret;
		
	}

}
