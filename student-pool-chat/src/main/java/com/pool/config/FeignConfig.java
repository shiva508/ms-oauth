package com.pool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {

	@Autowired
	OAuth2AuthorizedClientService auth2AuthorizedClientService;
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TOKEN_TYPE = "Bearer";

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			requestTemplate.header("Content-Type", "application/json");
			requestTemplate.header("Accept", "application/json");
			requestTemplate.header("header_1", "value_1");
			requestTemplate.header("header_2", "value_2");
			requestTemplate.header("header_3", "value_3");
			Authentication oauth =SecurityContextHolder.getContext().getAuthentication();
			OAuth2AuthenticationToken auth2AuthenticationToken=(OAuth2AuthenticationToken) oauth;
			OAuth2AuthorizedClient auth2AuthorizedClient =auth2AuthorizedClientService.loadAuthorizedClient(auth2AuthenticationToken.getAuthorizedClientRegistrationId(), auth2AuthenticationToken.getName());
			String token=auth2AuthorizedClient.getAccessToken().getTokenValue();
			System.out.println("GFADAA"+token);
			//requestTemplate.header("Authorization", token);
			requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
		};
	}
}
