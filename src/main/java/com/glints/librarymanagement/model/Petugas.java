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
@Table(name="petugas")
@SQLDelete(sql =
"UPDATE petugas " +
        "SET deleted = true " +
        "WHERE id = ?")
@Where(clause = "deleted = false")
public class Petugas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nama;
	private String password;
	private String userName;
	private boolean deleted;
	
	public Petugas() {

	}

	public Petugas(String nama, String password, String userName) {
		super();
		this.nama = nama;
		this.password = password;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

	
}
