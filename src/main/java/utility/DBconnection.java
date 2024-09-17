package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static Connection connection;
	
    public static Connection getConnection() throws ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			 connection =DriverManager.getConnection("jdbc:mysql:///CarRentalJspProjectMontran","root","root");
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return connection;
		
		
	}
	

}
