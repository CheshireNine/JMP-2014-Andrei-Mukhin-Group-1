package com.epam.j2eejms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Session Bean implementation class Currency
 */
@Entity(name = "currency")
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="`currency_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long currencyId;
    @Column(name="`rate`")
	private float rate;
    @Column(name="`precision`")
	private int precision;
	@ManyToOne(targetEntity = Bank.class)
	@JoinColumn(name="`bank_id`")
	private Bank bank;
	private String name;

	public Currency() {
		super();
	}

	public long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(long currencyId) {
		this.currencyId = currencyId;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrecision() {
		return precision;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}
}
