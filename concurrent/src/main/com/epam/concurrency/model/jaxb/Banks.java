package com.epam.concurrency.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.concurrency.model.Bank;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "banks")
public class Banks {

	@XmlElement(name = "bank", type = Bank.class)
	private List<Bank> banks = new ArrayList<Bank>();
	
	public Banks() {
	}

	public Banks(List<Bank> banks) {
		super();
		this.banks = banks;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

}
