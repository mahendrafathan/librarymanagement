package com.glints.librarymanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "author")
@SQLDelete(sql = "UPDATE author " + "SET deleted = true " + "WHERE book_id = ?")
@Where(clause = "deleted = false")
public class Author extends Persistence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "deleted")
	private boolean deleted;

	public Author() {
	}

	public Author(String firstname, String surname, String placeOfBirth, Date dateOfBirth,
					String contact, String email, String address) {
		this.firstname = firstname;
		this.surname = surname;
		this.placeOfBirth = placeOfBirth;
		this.dateOfBirth = dateOfBirth;
		this.contact = contact;
		this.email = email;
		this.address = address;
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
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
