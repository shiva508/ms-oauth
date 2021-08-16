package com.pool.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.model.CallbackResponse;
import com.pool.model.OktaResponse;
import com.pool.util.JsonConverter;

@RestController
@RequestMapping("/oauth")
public class OauthCallbackController {
	@GetMapping("/callback")
	public CallbackResponse callbackResponse(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code=request.getParameter("code");
		String state=request.getParameter("state");
		String sessionstate=request.getParameter("session_state");
		CallbackResponse callbackResponse=new CallbackResponse();
          
          HttpPost post = new HttpPost("http://localhost:8080/auth/realms/student_pool/protocol/openid-connect/token");
          List <NameValuePair> nvps = new ArrayList <NameValuePair>();
          nvps.add(new BasicNameValuePair("code", code));
          nvps.add(new BasicNameValuePair("state", "shivashiva"));
          nvps.add(new BasicNameValuePair("client_id", "student_pool"));
          nvps.add(new BasicNameValuePair("client_secret",  "b651d2ab-a291-49ce-b8c1-72d3f7063858"));
          nvps.add(new BasicNameValuePair("redirect_uri", "http://localhost:8002/student-pool-friends/oauth/callback"));
          nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));

          post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
          OktaResponse asaass= null;
          
          DefaultHttpClient httpClient = new DefaultHttpClient();
          HttpResponse response = httpClient.execute(post);
          System.out.println(response.getEntity().getContent());
          
          HttpEntity entity = response.getEntity();
          if (entity != null) {
              // return it as a String
              String result = EntityUtils.toString(entity);
              System.out.println(result);
              asaass=  JsonConverter.getSingletonLazyInstance().convertToJavaObject(result, OktaResponse.class);
              System.out.println(asaass);
          }
          System.out.println(response.getStatusLine());
		callbackResponse.setCode(code);
		callbackResponse.setState(state);
		callbackResponse.setSessionState(sessionstate);
		//Tes
		return callbackResponse;
	}
}
