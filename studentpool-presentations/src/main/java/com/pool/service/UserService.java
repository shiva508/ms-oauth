package com.pool.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pool.model.UserModel;

public interface UserService extends UserDetailsService {

	public UserModel createUser(UserModel userModel);

	public UserModel getUserById(String userId);

	public UserModel getUserByEmail(String email);

}
