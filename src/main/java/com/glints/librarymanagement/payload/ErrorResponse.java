package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
	@JsonProperty("error_message")
	private String errMessage;
	@JsonProperty("message")
	private String message;

	public ErrorResponse(String errMessage, String message) {
		super();
		this.errMessage = errMessage;
		this.message = message;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
