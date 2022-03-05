package com.pool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pool.model.CommonResponse;
import com.pool.proxy.MoneyFeignProxy;

@RestController
@RequestMapping("/presentation")
public class PresentaionController {
	
	@Autowired
	private MoneyFeignProxy moneyFeignProxy;  

	@GetMapping("/hello")
	public ResponseEntity<CommonResponse> helloPresentation() {
		return new ResponseEntity<>(new CommonResponse().setCode(508).setMessage("PRESENTATION"), HttpStatus.OK);
	}

	@GetMapping("/hello/money")
	public CommonResponse helloPresentationMoney() {
		
		return moneyFeignProxy.helloMoney().getBody();
	}
}
