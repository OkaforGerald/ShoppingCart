package com.onyeka.DAO;
import java.sql.*;

import com.onyeka.model.User;
public class UserDAO {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDAO(Connection con) {
		this.con=con;
	}
	
	public User getUser(String email,String password) {
		User user=null;
		String query="SELECT * FROM users WHERE email=? AND password=?";
		try {
			pst=this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
