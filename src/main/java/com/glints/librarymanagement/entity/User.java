package com.glints.librarymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.glints.librarymanagement.model.Persistence;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@SQLDelete(sql =
		"UPDATE employee " +
        "SET deleted = true " +
        "WHERE id = ?")
@Where(clause = "deleted = false")
public class User extends Persistence{
    @Id
    private int id;
    private String userName;
    private String password;
    private String name;

	public String getName() {
		return name;
	}
    public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		this.userName = userName;
	}
}
