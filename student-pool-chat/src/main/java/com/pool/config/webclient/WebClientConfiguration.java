package com.pool.config.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
	@Bean
	public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction filterFunction = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
				clientRegistrationRepository, auth2AuthorizedClientRepository);
		filterFunction.setDefaultOAuth2AuthorizedClient(true);
		return WebClient.builder().apply(filterFunction.oauth2Configuration()).build();
	}
}
