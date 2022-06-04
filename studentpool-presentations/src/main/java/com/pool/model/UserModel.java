package com.pool.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	private String userId;
	@NotNull(message = "First name should not be null or empty")
	@Size(min = 3, message = "Firstname should be mor the 3 char lenght ")
	private String firstName;
	@NotNull(message = "First name should not be null or empty")
	@Size(min = 1, message = "Firstname should be mor the 1 char lenght ")
	private String lastName;
	@NotNull(message = "password should not be null or empty")
	private String password;
	@NotNull(message = "email should not be null or empty")
	private String email;

	public UserModel() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
