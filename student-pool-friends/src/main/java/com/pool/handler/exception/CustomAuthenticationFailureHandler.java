package com.pool.handler.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pool.constants.OAuthConstants;
import com.pool.exception.model.HttpResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		HttpResponse httpResponse = new HttpResponse();
		httpResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
		httpResponse.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
		httpResponse.setReason(HttpStatus.UNAUTHORIZED.getReasonPhrase().toUpperCase());
		httpResponse.setMessage(OAuthConstants.OAUTH_TOKEN_ACCESS_DENIED_MSG);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		String jsonLoginResponse = new Gson().toJson(httpResponse);
		response.getWriter().print(jsonLoginResponse);

	}

}
