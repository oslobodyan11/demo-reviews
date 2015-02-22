package com.techaccent.reviews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "creator")
public class Creator {

	private String email;
	private String firstName;
	private String lastName;
	private String languge;
	private String openId;
	private String uuid;

	public String getEmail() {
		return email;
	}


	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}


	@XmlElement
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}


	@XmlElement
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLanguge() {
		return languge;
	}


	@XmlElement
	public void setLanguge(String languge) {
		this.languge = languge;
	}

	public String getOpenId() {
		return openId;
	}


	@XmlElement
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUuid() {
		return uuid;
	}


	@XmlElement
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
