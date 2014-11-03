package com.epam.concurrency.services;

import java.util.List;

import com.epam.concurrency.dao.IBankDAO;
import com.epam.concurrency.dao.ICurrencyDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.Currency;

public class CurrencyService {
	private ICurrencyDAO dao;
	private IBankDAO bankDAO;

	public CurrencyService() {
		super();
	}

	public List<Currency> getList() {
		List<Currency> currencies = null;
		try {
			currencies = dao.getList();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return currencies;
	}
	public long addCurrency(long bankId, String name, float rate, int precision) {
		Currency currency = new Currency();
		Bank bank;
		long currencyId = 0;
		try {
			bank = bankDAO.fetchById(bankId);
			currency.setBank(bank);
			currency.setName(name);
			currency.setRate(rate);
			currency.setPrecision(precision);
			currencyId = dao.save(currency);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		return currencyId;
	}

	public List<Currency> fetchByBankId(long id) {
		List<Currency> currencies = null;
		try {
			currencies = dao.fetchByBankId(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currencies;
	}
	
	public boolean editCurrency(int currencyId) {
		return true;
	}
	
	public boolean deleteCurrency(int currencyId) {
		return true;
	}

	public void setDao(ICurrencyDAO dao) {
		this.dao = dao;
	}

	public void setBankDAO(IBankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}

}
