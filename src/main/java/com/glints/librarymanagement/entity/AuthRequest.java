package com.glints.librarymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String userName;
    private String password;
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
}
