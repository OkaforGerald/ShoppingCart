package com.onyeka.model;

public class Product {
	private int prodID;
	private String prodName;
	private String category;
	private double price;
	private String image;
	public Product() {
		prodID=0;
		prodName="";
		category="";
		price=0;
		image="";
	}
	public Product(int prodID, String prodName, String category, double price, String image) {
		this.prodID = prodID;
		this.prodName = prodName;
		this.category = category;
		this.price = price;
		this.image = image;
	}
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
