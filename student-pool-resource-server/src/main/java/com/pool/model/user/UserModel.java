package com.pool.model.user;

public class UserModel {
	private String firtName;
	private String lastName;
	private String userId;

	public UserModel() {

	}

	public String getFirtName() {
		return firtName;
	}

	public UserModel setFirtName(String firtName) {
		this.firtName = firtName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserModel setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public UserModel setUserId(String userId) {
		this.userId = userId;
		return this;
	}



}
