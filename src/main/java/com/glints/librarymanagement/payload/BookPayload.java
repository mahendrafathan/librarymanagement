package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookPayload {
    @JsonProperty
    private String author;

    @JsonProperty
    private String title;

    @JsonProperty
    private int year;

    @JsonProperty
    private String publisher;

    @JsonProperty
    private String category;

    @JsonProperty
    private Integer quantity;

    public BookPayload(String author, String title, int year, String publisher, String category,  Integer quantity) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.category = category;
        this.quantity = quantity;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
