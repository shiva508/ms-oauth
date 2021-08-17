package com.pool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.model.user.UserModel;

@RestController
@RequestMapping("/method")
public class MethodLevelSecurityController {

	@Secured("ROLE_coach")
	@DeleteMapping("/deleteuser/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		return "user deleted wit id" + userId;

	}
	//@PreAuthorize("hasRole('student') or #userId==#jwt.subject")
	@PreAuthorize("hasAuthority('ROLE_student') or #userId==#jwt.subject")
	@DeleteMapping("/deleteuserpreauthorize/{userId}")
	public String deleteUserPreAuthorize(@PathVariable("userId") String userId,@AuthenticationPrincipal Jwt jwt) {
		return "user deleted wit id " + userId+""+jwt.getSubject();

	}
	
	@PostAuthorize("returnObject.userId==#jwt.subject")
	@GetMapping("/getuser/{userId}")
	public UserModel getUserDetails(@PathVariable("userId") String userId,@AuthenticationPrincipal Jwt jwt) {
		UserModel model=new UserModel().setFirtName("Shiva").setLastName("Dasari").setUserId("9cdaa551-d6c6-416b-9eb9-ca6007dc209a");
		return model;
	}

	@PreAuthorize("hasAuthority('ROLE_student')")
	@GetMapping("/getPreauthorize")
	public String verifyWithPreAuthorize() {
		return "GET PRE AUTHORIZE";
	}
	
}
