package com.techaccent.reviews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class Account {
	
	private String accountIdentifier;
	private String status;
	
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	@XmlElement
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	public String getStatus() {
		return status;
	}

	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
