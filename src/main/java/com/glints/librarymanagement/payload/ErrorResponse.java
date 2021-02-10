package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    @JsonProperty
    private String errorMessage;

    @JsonProperty
    private String message;

    public ErrorResponse(String errorMessage, String message) {
        this.errorMessage = errorMessage;
        this.message = message;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
