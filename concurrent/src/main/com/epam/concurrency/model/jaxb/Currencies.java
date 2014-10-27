package com.epam.concurrency.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.concurrency.model.Currency;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "currencies")
public class Currencies {

	@XmlElement(name = "currency", type = Currency.class)
	private List<Currency> currencies = new ArrayList<Currency>();
	
	public Currencies() {
	}

	public Currencies(List<Currency> currencies) {
		super();
		this.currencies = currencies;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

}
