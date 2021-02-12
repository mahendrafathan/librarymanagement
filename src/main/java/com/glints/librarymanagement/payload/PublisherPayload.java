package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublisherPayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("contact")
	private String contact;
	@JsonProperty("email")
	private String email;
	public PublisherPayload() {
		super();
	}
	public PublisherPayload(String name, String address, String contact, String email) {
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
	public String getContact() {
		return contact;
	}
	public String getEmail() {
		return email;
	}
}
