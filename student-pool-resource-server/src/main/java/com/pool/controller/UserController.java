package com.pool.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

	@GetMapping("/user")
	public ResponseEntity<?> getData() {
		Map<String, String> data=new HashMap<>();
		data.put("Data", "data");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
