package com.epam.concurrency.services;

import java.util.List;

import com.epam.concurrency.dao.BankXMLDAO;
import com.epam.concurrency.dao.ICurrencyDAO;
import com.epam.concurrency.dao.CurrencyXMLDAO;
import com.epam.concurrency.exceptions.DAOException;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.utils.ModelIdUtil;

public class CurrencyService {
	private static ICurrencyDAO dao = new CurrencyXMLDAO();

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
	public boolean addCurrency(long bankId, String name, float rate, int precision) {
		Currency currency = new Currency();
		BankXMLDAO bankDAO = new BankXMLDAO();
		Bank bank;
		try {
			bank = bankDAO.fetchById(bankId);
			List<Currency> currencies = dao.getList();
			long currencyId = ModelIdUtil.getMaxCurrencyId(currencies) + 1;
			currency.setBank(bank);
			currency.setCurrencyId(currencyId);
			currency.setName(name);
			currency.setRate(rate);
			currency.setPrecision(precision);
			dao.save(currency);
		} catch (DAOException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
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

}
