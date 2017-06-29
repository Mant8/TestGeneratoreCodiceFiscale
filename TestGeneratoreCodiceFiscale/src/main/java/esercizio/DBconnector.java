package esercizio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnector {
	
	private Statement cs;
	
	public Statement connectToDB() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			//192.168.237.174:3306
			//192.168.1.6
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.6/CodiceFiscale", "root", "tirocinio123");

			Statement createStatement = connection.createStatement();
			
			this.cs = createStatement;
			return createStatement;
			
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public ResultSet exeQuery(String query) {
		
		try {
			ResultSet resultSet = cs.executeQuery(query);
			
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		
	}

}
