package com.pool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pool.util.PkceUtil;

@Controller
public class StudentPoolChatController {

	@Autowired
	private PkceUtil pkceUtil; 
	@GetMapping("/chat")
	public String chatWelcome(Model model) {
		String codeVerifier=pkceUtil.codeVerifierGenerator();
		String codeChallenge=pkceUtil.codeChallengeGenarator(codeVerifier);
		System.out.println(codeVerifier);
		System.out.println(codeChallenge);
		return "chat";
	}
}
