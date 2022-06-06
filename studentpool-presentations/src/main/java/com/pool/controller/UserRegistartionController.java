package com.pool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.model.UserModel;
import com.pool.service.UserService;
import com.pool.util.PresentationUtil;

@RestController
@RequestMapping("/userprofile")
public class UserRegistartionController {

	@Autowired
	private UserService userService;

	@Autowired
	private PresentationUtil presentationUtil;

	@PostMapping("/createuser")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserModel createUserRequestModel, BindingResult result) {
		ResponseEntity<?> errorResponse = presentationUtil.validationResponseExtractorResult(result);
		if (errorResponse != null)
			return errorResponse;
		UserModel createdUserModel = userService.createUser(createUserRequestModel);
		return new ResponseEntity<>(createdUserModel, HttpStatus.CREATED);
	}

	@GetMapping("/byuserid/{userId}")
	public ResponseEntity<?> getUserById(String userId) {
		UserModel userModel = userService.getUserById(userId);
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}

	@GetMapping(path = "/byuseriddatatype/{userId}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUserByIdDataType(String userId) {
		UserModel userModel = userService.getUserById(userId);
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}
}
