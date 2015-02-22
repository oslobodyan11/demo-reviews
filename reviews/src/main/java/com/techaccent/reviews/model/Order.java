package com.techaccent.reviews.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
public class Order {

	private String editionCode;
	private List<Item> item;
	private String pricingDuration;

	public String getEditionCode() {
		return editionCode;
	}


	@XmlElement
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public List<Item> getItem() {
		return item;
	}

	@XmlElements(value = { @XmlElement(name = "item") })
	public void setItem(List<Item> item) {
		this.item = item;
	}

	public String getPricingDuration() {
		return pricingDuration;
	}


	@XmlElement
	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

}
