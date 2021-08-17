package com.pool.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/okta")
public class OAuthController {

	@GetMapping("/token")
	public ResponseEntity<?> getOthenticationDetails(@AuthenticationPrincipal Jwt jwt) {
		return new ResponseEntity<>(Collections.singletonMap("oauth", jwt.getTokenValue()), HttpStatus.OK);
	}
}
