package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberPayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("contact")
	private String contact;
	@JsonProperty("address")
	private String address;
	
	public MemberPayload(String name, String gender, String contact, String address) {
		super();
		this.name = name;
		this.gender = gender;
		this.contact = contact;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getContact() {
		return contact;
	}

	public String getAddress() {
		return address;
	}
}
