package com.glints.librarymanagement.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BorrowerPayload {

	@JsonProperty("member")
	private String member;
	@JsonProperty("employee")
	private String employee;
	@JsonProperty("book")
	private String book;
	@JsonProperty("return_date")
	private Date returnDate;

	public String getMember() {
		return member;
	}

	public String getEmployee() {
		return employee;
	}

	public String getBook() {
		return book;
	}

	public Date getReturnDate() {
		return returnDate;
	}

}
