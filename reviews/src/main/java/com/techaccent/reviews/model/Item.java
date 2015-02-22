package com.techaccent.reviews.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class Item {
	private Integer quanity;
	private String unit;

	public Integer getQuanity() {
		return quanity;
	}

	@XmlElement
	public void setQuanity(Integer quanity) {
		this.quanity = quanity;
	}

	public String getUnit() {
		return unit;
	}


	@XmlElement
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
