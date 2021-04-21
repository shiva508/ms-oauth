package com.pool.exception.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HttpResponse {
	private Integer httpStatusCode;
	private HttpStatus httpStatus;
	private String reason;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date timeStamp;

	public HttpResponse() {

	}

	public HttpResponse(Integer httpStatusCode, HttpStatus httpStatus, String reason, String message, Date timeStamp) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
