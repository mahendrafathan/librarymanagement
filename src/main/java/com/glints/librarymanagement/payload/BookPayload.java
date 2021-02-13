package com.glints.librarymanagement.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookPayload {
	@JsonProperty
	private Integer id;
	
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

    public BookPayload(Integer id, String author, String title, int year, String publisher, String category,  Integer quantity) {
    	this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.category = category;
        this.quantity = quantity;
    }
    
    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
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
