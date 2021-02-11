package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetugasPayload {
	@JsonProperty("id")
	private int id;
	@JsonProperty("nama")
	private String nama;
	@JsonProperty("password")
	private String password;
	@JsonProperty("userName")
	private String userName;
	//GETTER
	public int getId() {
		return id;
	}
	public String getNama() {
		return nama;
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	public void setId(int id) {
		this.id = id;
	}
		
	
	//CONSTRUCTOR TIDAK PERLU
	
	
}
