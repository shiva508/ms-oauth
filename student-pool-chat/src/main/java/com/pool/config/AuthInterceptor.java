package com.pool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class AuthInterceptor implements RequestInterceptor {

	@Autowired
	OAuth2AuthorizedClientService auth2AuthorizedClientService;
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TOKEN_TYPE = "Bearer";

	@Override
	public void apply(RequestTemplate template) {
		Authentication oauth = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) oauth;
		OAuth2AuthorizedClient auth2AuthorizedClient = auth2AuthorizedClientService.loadAuthorizedClient(
				auth2AuthenticationToken.getAuthorizedClientRegistrationId(), auth2AuthenticationToken.getName());
		String token = auth2AuthorizedClient.getAccessToken().getTokenValue();
		System.out.println("GFADAA" + token);
		template.header("Authorization", token);
		template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));

	}

}
