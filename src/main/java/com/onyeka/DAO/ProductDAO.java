package com.onyeka.DAO;

import com.onyeka.model.*;
import java.util.*;
import java.sql.*;

public class ProductDAO {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private String query;
	
	public ProductDAO(Connection con) {
		this.con=con;
	}
	public List<Product> getProducts(){
		List<Product> prod=new ArrayList<>();
		query="SELECT * FROM products";
		try {
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Product product=new Product();
				product.setProdID(rs.getInt(1));
				product.setProdName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getDouble(4));
				product.setImage(rs.getString(5));
				
				prod.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prod;
	}
	public ArrayList<Cart> getCartItems(List<Cart> cart_list){
		ArrayList<Cart> cartItems=new ArrayList<>();
		if(cart_list==null) {
			cartItems.add(new Cart());
		}
		for(Cart items:cart_list) {
			query="SELECT * FROM products where ProdID=?";
			try {
				pst=con.prepareStatement(query);
				pst.setInt(1, items.getProdID());
				rs=pst.executeQuery();
				while(rs.next()) {
					Cart cart=new Cart();
					cart.setProdID(rs.getInt(1));
					cart.setProdName(rs.getString(2));
					cart.setCategory(rs.getString(3));
					cart.setPrice(rs.getDouble(4)*items.getQuantity());
					cart.setQuantity(items.getQuantity());
					
					
					cartItems.add(cart);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cartItems;
	}
}
