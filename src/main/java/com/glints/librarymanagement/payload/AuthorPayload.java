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
}
