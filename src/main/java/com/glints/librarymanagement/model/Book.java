package com.glints.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@SQLDelete(sql =
        "UPDATE book " +
                "SET deleted = true " +
                "WHERE book_id = ?")
@Where(clause = "deleted = false")
public class Book extends Persistence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer book_id;

    @JoinColumn(name = "author_id", nullable = false)
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private Author author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year", nullable = false)
    private int year;

    @JoinColumn(name = "publisher_id", nullable = false)
    @ManyToOne(targetEntity = Publisher.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private Publisher publisher;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    private boolean deleted;

    public Book() {
    }

    public Book(Author author, String title, int year, Publisher publisher, String category, Integer quantity) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.category = category;
        this.quantity = quantity;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
