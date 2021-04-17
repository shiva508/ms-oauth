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
	@GetMapping("/callback")
	public CallbackResponse callbackResponse(HttpServletRequest request) {
		String code=request.getParameter("code");
		String state=request.getParameter("state");
		String sessionstate=request.getParameter("session_state");
		CallbackResponse callbackResponse=new CallbackResponse();
		callbackResponse.setCode(code);
		callbackResponse.setState(state);
		callbackResponse.setSessionState(sessionstate);
		return callbackResponse;
	}
}
