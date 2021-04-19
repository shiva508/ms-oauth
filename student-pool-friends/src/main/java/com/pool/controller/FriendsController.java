package com.pool.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.model.CallbackResponse;

@RestController
@RequestMapping("/friends")
public class FriendsController {
	@GetMapping("/list")
	public List<String> friendsList() {
		return List.of("Shiva", "Nithya");
	}
}
