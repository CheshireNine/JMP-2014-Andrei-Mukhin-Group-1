/**
 * 
 */
package com.epam.concurrency.model;

/**
 * @author I7eter
 *
 */
public class Currency {

	/**
	 * Currency
	 */
	private long currencyId;
	private float rate;
	private int precision;
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
