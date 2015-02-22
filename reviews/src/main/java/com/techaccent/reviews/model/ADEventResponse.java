package com.techaccent.reviews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class ADEventResponse {
	private boolean success;
	private String message;
	private String accountIdentifier;
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}

	@XmlElement
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	public boolean isSuccess() {
		return success;
	}

	@XmlElement
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}
	
	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	
	@XmlElement
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
}
