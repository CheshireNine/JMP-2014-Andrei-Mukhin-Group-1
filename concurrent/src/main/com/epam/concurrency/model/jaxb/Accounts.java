package com.epam.concurrency.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.concurrency.model.Account;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "accounts")
public class Accounts {

	@XmlElement(name = "account", type = Account.class)
	private List<Account> accounts = new ArrayList<Account>();
	
	public Accounts() {
	}

	public Accounts(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
