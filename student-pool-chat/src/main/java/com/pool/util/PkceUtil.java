package com.pool.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class PkceUtil {
	public String codeVerifierGenerator() {
		SecureRandom random = new SecureRandom();
		byte[] codeVerifier = new byte[32];
		random.nextBytes(codeVerifier);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
	}

	public String codeChallengeGenarator(String codeVerifier) {
		String codeChallenge="";
		try {
			byte[] bytes = codeVerifier.getBytes("US-ASCII");
			MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
			messageDigest.update(bytes, 0, bytes.length);
			byte[] digest=messageDigest.digest();
			codeChallenge=Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codeChallenge;
	}
}
