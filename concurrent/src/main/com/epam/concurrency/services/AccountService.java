package com.epam.concurrency.services;

import java.util.List;

import com.epam.concurrency.dao.IAccountDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Currency;

public class AccountService {
	private IAccountDAO dao;

	public AccountService() {
		super();
	}

	public List<Account> getList() {
		List<Account> accounts = null;
		try {
			accounts = dao.getList();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	public long addAccount(Currency currency, long amount) {
		Account account = new Account();
		account.setCurrency(currency);
		account.setAmount(amount);
		long accountId = 0;
		try {
			accountId = dao.save(account);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountId;
	}
	
	public Account fetchById(long id) {
		try {
			return dao.fetchById(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Account();
	}
	
	public boolean editAccount(Account account) {
		try {
			return dao.edit(account);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAccount(int accountId) {
		try {
			return dao.remove(accountId);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void setDao(IAccountDAO dao) {
		this.dao = dao;
	}

}
