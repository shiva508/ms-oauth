package com.pool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.model.CommonResponse;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
	@Autowired
	private Environment env;
	
	@GetMapping("/hellourlrewrite")
	public ResponseEntity<CommonResponse> urlRewrite(){
		String port=env.getProperty("local.server.port");
		return new ResponseEntity<>(new CommonResponse().setCode(508).setMessage("PRESENTATION:url rewrite").setPort(port), HttpStatus.OK);
	}
}
