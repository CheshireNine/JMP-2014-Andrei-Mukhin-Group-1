/**
 * 
 */
package com.epam.concurrency.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author I7eter
 *
 */
@XmlRootElement
public class Account {

	private long accountId;
	private long amount;
	private Person owner;
	private Currency currency;

	/**
	 * 
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
