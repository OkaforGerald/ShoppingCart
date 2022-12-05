package com.onyeka.model;

public class User {
	private int id;
	private String FirstName;
	private String LastName;
	private String email;
	private String password;
	public User() {
		id=0;
		FirstName="";
		LastName="";
		email="";
		password="";
	}
	public User(int id, String FirstName,String LastName, String email, String password) {
		this.id = id;
		this.FirstName = FirstName;
		this.LastName=LastName;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return FirstName;
	}
	public void setName(String name) {
		this.FirstName = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
