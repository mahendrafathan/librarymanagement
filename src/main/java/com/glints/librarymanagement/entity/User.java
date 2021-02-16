package com.glints.librarymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class User {
    @Id
    private int id;
    private String userName;
    private String password;
    private String name;
//	public User(String userName, String password, String name) {
//		// TODO Auto-generated constructor stub
//	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
    public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		this.userName = userName;
	}
}
