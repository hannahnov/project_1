package javalin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static final String URL = System.getenv("P1_URL");
	private static final String USERNAME = System.getenv("P1_USERNAME");
	private static final String PASSWORD = System.getenv("P1_PASSWORD");
		
	private static Connection conn;
	
	public Connection createConnection() throws SQLException {
			
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?", "postgres", "Ripeanut1!");
			
			return conn;
			
		}
	
}

