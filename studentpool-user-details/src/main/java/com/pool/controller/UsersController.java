package com.pool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.response.UserRest;
import com.pool.response.VerifyPasswordResponse;
import com.pool.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService usersService;

	@GetMapping("/{userName}")
	public UserRest getUser(@PathVariable("userName") String userName) {
		UserRest rest=usersService.getUserDetails(userName);
		System.out.println(rest.getEmail());
		return rest;

	}

	@PostMapping("/{userName}/verify-password")
	public VerifyPasswordResponse verifyUserPassword(@PathVariable("userName") String userName,
			@RequestBody String password) {

		VerifyPasswordResponse returnValue = new VerifyPasswordResponse(false);

		UserRest user = usersService.getUserDetails(userName, password);
System.out.println(user.getUserName());
		if (user != null) {
			returnValue.setResult(true);
		}

		return returnValue;
	}
}
