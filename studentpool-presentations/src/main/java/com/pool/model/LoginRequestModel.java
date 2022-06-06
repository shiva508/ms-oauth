package com.pool.model;

public class LoginRequestModel {
	private String username;
	private String password;

	public LoginRequestModel() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequestModel [username=" + username + ", password=" + password + "]";
	}

}
