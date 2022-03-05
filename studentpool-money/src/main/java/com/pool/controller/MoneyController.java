package com.pool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.model.CommonResponse;

@RestController
@RequestMapping("/money")
public class MoneyController {
	@GetMapping("/hellomoney")
	public ResponseEntity<CommonResponse> helloMoney() {
		return new ResponseEntity<>(new CommonResponse().setCode(508).setMessage("MONEY"), HttpStatus.OK);
	}
}
