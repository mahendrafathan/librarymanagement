package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublisherPayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("contact")
	private long contact;
	@JsonProperty("email")
	private String email;
	public PublisherPayload() {
		super();
	}
	public PublisherPayload(String name, String address, long contact, String email) {
		super();
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public long getContact() {
		return contact;
	}
	public String getEmail() {
		return email;
	}
	
	
}
