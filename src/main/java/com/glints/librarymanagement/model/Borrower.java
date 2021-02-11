package com.glints.librarymanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrowing")
public class Borrower {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	// should be join
	@Column(name = "member_id", nullable = false)
	private String memberId;
	// should be join
	@Column(name = "employee_id", nullable = false)
	private String employeeId;
	// should be join
	@Column(name = "book_id", nullable = false)
	private String bookId;

	@Column(name = "borrow_date", nullable = false)
	private Date borrowDate;

	@Column(name = "return_date", nullable = false)
	private Date returnDate;

	@Column(name = "is_returned", nullable = false)
	private Boolean isReturned;

	public Borrower() {
		super();
	}

	public Borrower(String id, String memberId, String employeeId, String bookId, Date borrowDate, Date returnDate,
			Boolean isReturned) {
		this.id = id;
		this.memberId = memberId;
		this.employeeId = employeeId;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.isReturned = isReturned;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
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

}
