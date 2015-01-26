package com.epam.j2eejms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Session Bean implementation class Bank
 */
@Entity(name = "bank")
public class Bank implements Serializable{
private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bank_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bankId;
	private String name;

	public Bank() {
		super();
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
