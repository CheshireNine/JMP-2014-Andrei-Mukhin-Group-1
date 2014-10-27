/**
 * 
 */
package com.epam.concurrency.model;

/**
 * @author I7eter
 *
 */
public class Bank {

	private long bankId;
	private String name;

	/**
	 * 
	 */
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
