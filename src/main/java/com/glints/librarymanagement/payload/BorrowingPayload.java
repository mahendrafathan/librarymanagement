package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BorrowingPayload {

	@JsonProperty("member")
	private String memberId;
	@JsonProperty("employee")
	private String employeeId;
	@JsonProperty("book")
	private String bookId;

	public String getMemberId() {
		return memberId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getBookId() {
		return bookId;
	}

}
