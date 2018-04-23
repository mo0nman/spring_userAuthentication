package com.optimum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.optimum.connection.DatabaseConnection;
import com.optimum.model.User;

public class UserDao {
	
	private User refUser;
	private Connection con;
	
	public UserDao() {
		con = DatabaseConnection.getConnection();
	}
	
	public boolean loginAuthenticator(User refUser) throws SQLException {
		
		PreparedStatement stmnt = con.prepareStatement("SELECT Name,Password FROM spring_jndi_test WHERE Name=? AND Password=?");
		
		String userName = refUser.getUserName();
		String userPassword = refUser.getUserPassword();
		
		stmnt.setString(1, userName);
		stmnt.setString(2, userPassword);
		
		ResultSet rs = stmnt.executeQuery();
		
		while(rs.next()) {
			String name = rs.getString("Name");
			String password = rs.getString("Password");
			
			if(name.equals(userName) && password.equals(userPassword)) {
				return true;
			}
		}
		return false;
	}
}
