package com.pool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import com.pool.config.util.RoleExtractor;
import com.pool.constants.OAuthConstants;
import com.pool.handler.exception.OauthAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class StudentPoolSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OauthAccessDeniedHandler oauthAccessDeniedHandler;
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleExtractor());
		return authenticationConverter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests((requests) -> requests.antMatchers(HttpMethod.GET,
		 * OAuthConstants.URL_USER).hasRole(OAuthConstants.ROLE_STUDENT)
		 * .antMatchers(HttpMethod.DELETE,
		 * OAuthConstants.URL_METHOD).hasAnyRole(OAuthConstants.ROLE_STUDENT,
		 * OAuthConstants.ROLE_COACH) // .hasRole("student") //
		 * .hasAnyAuthority("SCOPE_email")
		 * .anyRequest().authenticated().and().exceptionHandling().accessDeniedHandler(
		 * oauthAccessDeniedHandler)).an.oauth2ResourceServer( (authorize) ->
		 * authorize.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())).
		 * exceptionHandling().accessDeniedHandler(oauthAccessDeniedHandler);
		 */
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, OAuthConstants.URL_USER).hasRole(OAuthConstants.ROLE_STUDENT)
		.antMatchers(HttpMethod.DELETE, OAuthConstants.URL_METHOD).hasAnyRole(OAuthConstants.ROLE_STUDENT,OAuthConstants.ROLE_COACH)
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.accessDeniedHandler(oauthAccessDeniedHandler)
		.and()
		.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
	}
}
