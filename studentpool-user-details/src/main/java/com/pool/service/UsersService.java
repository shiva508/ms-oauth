package com.pool.service;

import com.pool.response.UserRest;

public interface UsersService {
	UserRest getUserDetails(String userName, String password);

	UserRest getUserDetails(String userName);
}
