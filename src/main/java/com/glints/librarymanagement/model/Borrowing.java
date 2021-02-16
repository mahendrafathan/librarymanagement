package com.glints.librarymanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glints.librarymanagement.entity.User;

@Entity
@Table(name = "borrowing")
@SQLDelete(sql = "UPDATE borrowing " + "SET deleted = true " + "WHERE id = ?")
@Where(clause = "deleted = false")
public class Borrowing extends Persistence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(name = "member_id", nullable = false)
	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Member member;

	@JoinColumn(name = "employee_id", nullable = false)
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private User employee;

	@JoinColumn(name = "book_id", nullable = false)
	@ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Book book;

	@Column(name = "borrow_date")
	private Date borrowDate;

	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "is_returned")
	private Boolean isReturned;

	private boolean deleted;

	public Borrowing() {
		super();
	}

	public Borrowing(Long id, Member member, User employee, Book book, Date borrowDate, Date returnDate,
			Boolean isReturned) {
		this.member = member;
		this.employee = employee;
		this.book = book;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.isReturned = isReturned;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Boolean getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(Boolean isReturned) {
		this.isReturned = isReturned;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
