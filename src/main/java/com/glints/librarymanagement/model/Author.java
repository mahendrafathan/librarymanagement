package com.glints.librarymanagement.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "isDeleted")
    private boolean isDeleted;

//    @JoinColumn(name = "book_id")
//    @ManyToMany(targetEntity = Book.class, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Book book;

    public Author() {
    }

    public Author(String firstname, String surname, String placeOfBirth, Date dateOfBirth, String contact, String email, String address, boolean isDeleted) {
        this.firstname = firstname;
        this.surname = surname;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
=======
import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author extends Persistence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long author_id;

    @Column(name = "author_name")
    private String authorName;

    public Author() {}

    public Author(String author_name) {
        this.authorName = author_name;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
>>>>>>> refs/remotes/origin/jecky-book
    }
}
