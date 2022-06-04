package com.pool.model;

public class CommonResponse {
	private String message;
	private Integer code;
	
	private String port;

	public CommonResponse() {
	}

	public String getMessage() {
		return message;
	}

	public CommonResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public CommonResponse setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getPort() {
		return port;
	}

	public CommonResponse setPort(String port) {
		this.port = port;
		return this;
	}

	
}
