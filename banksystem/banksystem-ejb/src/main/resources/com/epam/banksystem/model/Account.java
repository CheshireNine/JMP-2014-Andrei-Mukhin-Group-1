package com.epam.banksystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Session Bean implementation class Account
 */

@Entity(name = "account")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @Column
    private long amount;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name="person_id")
    private Person owner;

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name="currency_id")
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
