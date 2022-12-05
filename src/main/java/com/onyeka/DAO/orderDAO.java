package com.onyeka.DAO;

import java.sql.*;

import com.onyeka.model.*;

public class orderDAO {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public orderDAO(Connection con) {
		this.con=con;
	}
	
	public boolean insertOrder(Orders order) {
		boolean result = false;
		try {
			String Query="insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			pst=con.prepareStatement(Query);
			pst.setInt(1, order.getProdID());
			pst.setInt(2, order.getUid());
			pst.setInt(3, order.getQuantity());
			pst.setString(4, order.getDate());
			int count=pst.executeUpdate();
			if(count>=1) {
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
