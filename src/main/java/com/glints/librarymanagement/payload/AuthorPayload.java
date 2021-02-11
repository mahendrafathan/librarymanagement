package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class AuthorPayload {
	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("surname")
	private String surname;

	@JsonProperty("place_of_birth")
	private String placeOfBirth;

	@JsonProperty("date_of_birth")
	private String dateOfBirth;

	@JsonProperty("contact")
	private String contact;

	@JsonProperty("email")
	private String email;

	@JsonProperty("address")
	private String address;

	public AuthorPayload() {
		super();
	}

	public AuthorPayload(String firstname, String surname, String placeOfBirth, String dateOfBirth, String contact,
			String email, String address) {
		this.firstname = firstname;
		this.surname = surname;
		this.placeOfBirth = placeOfBirth;
		this.dateOfBirth = dateOfBirth;
		this.contact = contact;
		this.email = email;
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getContact() {
		return contact;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

}
