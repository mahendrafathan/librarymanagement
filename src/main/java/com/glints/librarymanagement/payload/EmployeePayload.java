package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeePayload {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("password")
	private String password;
	@JsonProperty("user_name")
	private String userName;
	//GETTER
//	public int getId() {
//		return id;
//	}
//	public String getName() {
//		return name;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
		
	
}
