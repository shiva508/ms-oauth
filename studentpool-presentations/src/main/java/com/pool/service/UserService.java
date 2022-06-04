package com.pool.service;

import com.pool.model.UserModel;

public interface UserService {

	public UserModel createUser(UserModel userModel);

	public UserModel getUserById(String userId);
}
