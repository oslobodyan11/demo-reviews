package com.techaccent.reviews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "marketplace")
public class Marketplace {
	
	private String baseUrl;
	private String partner;

	public String getBaseUrl() {
		return baseUrl;
	}


	@XmlElement
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getPartner() {
		return partner;
	}


	@XmlElement
	public void setPartner(String partner) {
		this.partner = partner;
	}

}
