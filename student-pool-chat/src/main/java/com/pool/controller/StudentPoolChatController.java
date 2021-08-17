package com.pool.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.pool.feign.proxy.FriendsFeignClient;
import com.pool.feign.proxy.StudentPoolRessourceProxy;
import com.pool.modal.ChatModal;
import com.pool.util.PkceUtil;

@Controller
public class StudentPoolChatController {
	private List chats;
	@Autowired
	private PkceUtil pkceUtil;

	@Autowired
	private OAuth2AuthorizedClientService auth2AuthorizedClientService;

	@Autowired
	private FriendsFeignClient friendsFeignClient;

	@Autowired
	private StudentPoolRessourceProxy studentPoolRessourceProxy;
	
	@Autowired
	private WebClient webClient;

	@GetMapping("/chat")
	public String chatWelcome(Model model, @AuthenticationPrincipal OidcUser oidcUser) {

		System.out.println(oidcUser);
		OidcIdToken idToken = oidcUser.getIdToken();

		String tokenValue = idToken.getTokenValue();
		System.out.println(tokenValue);
		Authentication oauth = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) oauth;
		OAuth2AuthorizedClient auth2AuthorizedClient = auth2AuthorizedClientService.loadAuthorizedClient(
				auth2AuthenticationToken.getAuthorizedClientRegistrationId(), auth2AuthenticationToken.getName());
		String token = auth2AuthorizedClient.getAccessToken().getTokenValue();
		System.out.println(token);
		String codeVerifier = pkceUtil.codeVerifierGenerator();
		String codeChallenge = pkceUtil.codeChallengeGenarator(codeVerifier);
		System.out.println(codeVerifier);
		System.out.println(codeChallenge);
		List<String> data = friendsFeignClient.friendsList();
		System.out.println("Oauth" + data);
		model.addAttribute("chats", chats);
		return "chat";
	}

	@GetMapping("/extracttoken")
	@ResponseBody
	public ResponseEntity<?> getOthenticationDetails(@AuthenticationPrincipal Jwt jwt) {
		String tokenValue = (String) studentPoolRessourceProxy.getOthenticationDetails(jwt).getBody().toString();
		System.out.println("SAAL::::"+studentPoolRessourceProxy.getOthenticationDetails(jwt));
		return new ResponseEntity<>(Collections.singletonMap("OAUTH_TOKEN", tokenValue), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	@ResponseBody
	public String deleteUser(@PathVariable("userId") Integer userId) {
		String data =studentPoolRessourceProxy.deleteUser(userId);
		return data;
	}
	
	@GetMapping("/getPreauthorize")
	@ResponseBody
	public String verifyWithPreAuthorize() {
		String data=studentPoolRessourceProxy.verifyWithPreAuthorize();
		return data;
	}
	@GetMapping("/getPreauthorizeusingwebclient")
	@ResponseBody
	public String usingWebClient() {
		String data=webClient.get().uri("http://localhost:8001/student-pool-resource-server/method/getPreauthorize").retrieve().bodyToMono(new ParameterizedTypeReference<String>(){}).block();
		return data;
	}

	@PostConstruct
	public void preInitializeData() {
		chats = new ArrayList<>();
		chats.add(new ChatModal().setMessageFrom("Satish").setMessage("Hi !"));
		chats.add(new ChatModal().setMessageFrom("Ravi").setMessage("Hi!,How are you"));
	}

}
