package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

	private static ConnectionManager singleton;
	private Connection con;

	private ConnectionManager() {
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/sicar", "postgres", "");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public static ConnectionManager getConnectionManager(){

		if (singleton == null) {
			singleton = new ConnectionManager();
		}

		return singleton;
	}

	public PreparedStatement getPrepareStatement(String sql) {
		PreparedStatement pst = null;
		
		try {
			pst =  this.con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return pst;
	}

}
