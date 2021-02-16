package com.glints.librarymanagement.payload;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
	public EmployeePayload(int id, String name, String password, String userName) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	
}
