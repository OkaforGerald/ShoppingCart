package com.onyeka.connection;
import java.sql.*;
public class DBConnection {
	static Connection connection=null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ECOMMERCEDB","root","meandsunshine200");
			System.out.println("Connection to DB successful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
