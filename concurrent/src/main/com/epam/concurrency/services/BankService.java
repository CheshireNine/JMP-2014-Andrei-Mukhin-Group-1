package com.epam.concurrency.services;

import java.util.List;

import com.epam.concurrency.dao.IBankDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;

public class BankService {
	private IBankDAO dao;

	public BankService() {
		super();
	}

	public List<Bank> getList() {
		List<Bank> banks = null;
		try {
			banks = dao.getList();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return banks;
	}
	public long addBank(String name) {
		Bank bank = new Bank();
		bank.setName(name);
		long bankId = 0;
		try {
			bankId = dao.save(bank);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bankId;
	}

	public boolean editBank(int bankId) {
		return true;
	}
	
	public boolean deleteBank(int bankId) {
		return true;
	}

	public void setDao(IBankDAO dao) {
		this.dao = dao;
	}

}
