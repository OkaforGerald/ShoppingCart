package com.onyeka.DAO;
import java.sql.*;
public class RegisterDAO {
	private int rowCount;
	private Connection con;
	private PreparedStatement pmt;
	
	public RegisterDAO(Connection con) {
		this.con=con;
	}
	
	public boolean registerUser(String FirstName,String LastName,String Email,String Password) {
		boolean isRegistered=false;
		String query="INSERT INTO users(FirstName,LastName,Email,Password) VALUES (?,?,?,?)";
		try {
			pmt=con.prepareStatement(query);
			pmt.setString(1, FirstName);
			pmt.setString(2, LastName);
			pmt.setString(3, Email);
			pmt.setString(4, Password);
			rowCount=pmt.executeUpdate();
			if(rowCount>0) {
				isRegistered=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isRegistered;
	}
}
