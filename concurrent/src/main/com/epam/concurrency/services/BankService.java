package com.epam.concurrency.services;

import java.util.List;

import com.epam.concurrency.dao.IBankDAO;
import com.epam.concurrency.dao.BankXMLDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;

public class BankService {
	private static IBankDAO dao = new BankXMLDAO();

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
	public boolean addBank(String name) {
		Bank bank = new Bank();
		bank.setName(name);
		try {
			dao.save(bank);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean editBank(int bankId) {
		return true;
	}
	
	public boolean deleteBank(int bankId) {
		return true;
	}

}
