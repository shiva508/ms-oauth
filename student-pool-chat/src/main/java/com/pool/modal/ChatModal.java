package com.pool.modal;

import java.io.Serializable;

public class ChatModal implements Serializable {
	private String messageFrom;
	private String message;

	public ChatModal() {

	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public ChatModal setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ChatModal setMessage(String message) {
		this.message = message;
		return this;
	}

}
