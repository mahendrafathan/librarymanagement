package com.glints.librarymanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher extends Persistence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long publisher_id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "contact")
	private String contact;

	@Column(name = "email")
	private String email;

	public Publisher() {
	}

	public Publisher(String publisher_name) {
		this.name = publisher_name;
	}

	public Long getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(Long publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getPublisherName() {
		return name;
	}

	public void setPublisherName(String publisherName) {
		this.name = publisherName;
	}
}
