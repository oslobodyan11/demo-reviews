package com.techaccent.reviews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "company")
public class Company {

	private String country;
	private String email;
	private String name;
	private String phoneNumber;
	private String uuid;
	private String website;

	public String getCountry() {
		return country;
	}


	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}


	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}


	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}


	@XmlElement
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUuid() {
		return uuid;
	}


	@XmlElement
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWebsite() {
		return website;
	}


	@XmlElement
	public void setWebsite(String website) {
		this.website = website;
	}

}
