package com.pool.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pool.modal.ChatModal;
import com.pool.util.PkceUtil;

@Controller
public class StudentPoolChatController {
private List chats;
	@Autowired
	private PkceUtil pkceUtil;

	@GetMapping("/chat")
	public String chatWelcome(Model model) {
		String codeVerifier = pkceUtil.codeVerifierGenerator();
		String codeChallenge = pkceUtil.codeChallengeGenarator(codeVerifier);
		System.out.println(codeVerifier);
		System.out.println(codeChallenge);
		model.addAttribute("chats", chats);
		return "chat";
	}
	
	@PostConstruct
	public void preInitializeData() {
		chats=new ArrayList<>();
		chats.add(new ChatModal().setMessageFrom("Satish").setMessage("Hi !"));
		chats.add(new ChatModal().setMessageFrom("Ravi").setMessage("Hi!,How are you"));
	}

}
