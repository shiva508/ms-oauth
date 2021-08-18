package com.pool.controller.login;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocialLoginController {

	@GetMapping("/home")
	public String homePage(@AuthenticationPrincipal OAuth2User auth2User,Model model) {
		if(auth2User !=null) {
			String name=auth2User.getAttribute("name");
			model.addAttribute("name", name);
		}
		
		
		return "home";
	}
}
