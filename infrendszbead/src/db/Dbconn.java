package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconn {
	private static Connection connect = null;
	
	
	public Connection Connect() throws ClassNotFoundException {
	String url = "jdbc:mysql://localhost:3306/vehicledb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	String username = "";
	String password = "";

	try {

		Class.forName("com.mysql.cj.jdbc.Driver");

		connect = DriverManager.getConnection(url, username, password);
		

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	return connect;
	}
}
