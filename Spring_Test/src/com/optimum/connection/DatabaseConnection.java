package com.optimum.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connection Established Successfull");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("1");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("2");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("3");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("4");
            }
            return connection;
        }
    }
}
