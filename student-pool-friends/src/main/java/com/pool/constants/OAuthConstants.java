package com.pool.constants;

import java.util.ArrayList;
import java.util.List;

public class OAuthConstants {
	public static final int OAUTH_TOKEN_EXPIRATION_TIME = 180000;
	public static final int OAUTH_REFRESH_TOKEN_EXPIRATION_TIME = 360000;
	public static final String OAUTH_TOKEN_PREFIX = "Bearer ";
	public static final String OAUTH_TOKEN_HEADER = "Jwt-Token";
	public static final String OAUTH_TOKEN_CAN_NOT_BE_VERIFIED = "Token can not be verified.";
	public static final String OAUTH_TOKEN_GET_ARRAYS_LLC = "Get Arrays,LLC";
	public static final String OAUTH_TOKEN_GET_ARRAYS_ADMINISTRATION = "User Management Portal";
	public static final String OAUTH_TOKEN_AUTHORITIES = "Authorities";
	public static final String OAUTH_TOKEN_FORBIDDEN_MSG = "Your Token has been expaired,You need to login to access this page,Please Login ";
	public static final String OAUTH_TOKEN_ACCESS_DENIED_MSG = "You do not have permission to acess this page";
	public static final String OAUTH_TOKEN_OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String OAUTH_TOKEN_PUBLIC_URLS[] = { "/oauth/callback"};
	public static final String OAUTH_TOKEN_PUBLIC_URLS_ALL[] = { "**" };

	public static List<String> bypassUrls = null;

	public static List<String> bypassUrls() {
		if (bypassUrls == null) {
			bypassUrls = new ArrayList<>();
			bypassUrls.add("oauthuser/oauthLogin");
		}
		return bypassUrls;
	}
	
	public static final String ROLE_STUDENT="student";
	public static final String ROLE_COACH="coach";
	public static final String URL_FRIENDS="/friends/**";
	public static final String URL_METHOD="/method/delete/**";
	public static final String APP_CONTEXT_PATH="/student-pool-friends";
}
