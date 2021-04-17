package com.pool.model;

public class CallbackResponse {
	private String state;
	private String sessionState;
	private String code;
	public String getState() {
		return state;
	}
	public CallbackResponse setState(String state) {
		this.state = state;
		return this;
	}
	public String getSessionState() {
		return sessionState;
	}
	public CallbackResponse setSessionState(String sessionState) {
		this.sessionState = sessionState;
		return this;
	}
	public String getCode() {
		return code;
	}
	public CallbackResponse setCode(String code) {
		this.code = code;
		return this;
	}

}
