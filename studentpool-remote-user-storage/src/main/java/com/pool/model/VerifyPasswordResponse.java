package com.pool.model;

public class VerifyPasswordResponse {
	
	public VerifyPasswordResponse() {
		
	}

	private boolean result;

	public VerifyPasswordResponse(boolean result) {
		this.result = result;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
