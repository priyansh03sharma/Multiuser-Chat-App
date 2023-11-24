package com.priyansh.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import static com.priyansh.chatapp.utils.ConfigReader.getValue;

public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//Step-1 Load a Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Step-2 Making a Connection
		final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/chatdb";
		final String USER_ID = "root";
		final String PASSWORD = "Priyansh@123";
		Connection con = DriverManager.getConnection(CONNECTION_STRING,USER_ID, PASSWORD);
		if(con != null) {
			System.out.println("Connection Created...");
			//con.close();
		}
		return con;
	}
	
}
