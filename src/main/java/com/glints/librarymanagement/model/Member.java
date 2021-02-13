package com.glints.librarymanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "member")
@SQLDelete(sql = "UPDATE member " + "SET deleted = true " + "WHERE id = ?")
@Where(clause = "deleted = false")
public class Member extends Persistence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "contact", nullable = false)
	private String contact;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "deleted")
	private boolean deleted;	// soft delete could be no getter-setter

	public Member() {
		super();
	}

	public Member(String name, String gender, String contact, String address) {
		this.name = name;
		this.gender = gender;
		this.contact = contact;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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
