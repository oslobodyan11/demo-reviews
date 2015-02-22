package com.techaccent.reviews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
public class Entry {

	private String key;
	private String value;

	public String getKey() {
		return key;
	}


	@XmlElement
	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}


	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}

}
