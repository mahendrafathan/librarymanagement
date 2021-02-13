package com.glints.librarymanagement.payload;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BorrowingPayload {

	@JsonProperty("member")
	private String member;
	@JsonProperty("employee")
	private String employee;
	@JsonProperty("book")
	private String book;
	@JsonProperty("borrow_date")
	private Date borrowDate;
	@JsonProperty("return_date")
	private Date returnDate;

	// declare current date
	Date currentDate = new Date();
	// make 3 days from current date
	Date dayAfter = new Date(currentDate.getTime() + TimeUnit.DAYS.toMillis(4));

	public String getMember() {
		return member;
	}

	public String getEmployee() {
		return employee;
	}

	public String getBook() {
		return book;
	}

	public Date getBorrowDate() {
		return currentDate;
	}

	public Date getReturnDate() {
		return dayAfter;
	}

}
