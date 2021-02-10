package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookPayload {
    @JsonProperty
    private Integer book_id;

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

    public BookPayload(Integer book_id, String author, String title, int year, String publisher, String category,  Integer quantity) {
        this.book_id = book_id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.category = category;
        this.quantity = quantity;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getBook_id() {
        return book_id;
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
