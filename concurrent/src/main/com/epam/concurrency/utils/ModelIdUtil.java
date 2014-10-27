package com.epam.concurrency.utils;

import java.util.List;

import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.model.Person;

public final class ModelIdUtil {

	private ModelIdUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static long getMaxCurrencyId(List<Currency> currencies) {
		long maxId = 0;
		for(Currency currency : currencies) {
			if (currency.getCurrencyId() > maxId) {
				maxId = currency.getCurrencyId();
			}
		}
		return maxId;
	}
	
	public static long getMaxAccountId(List<Account> accounts) {
		long maxId = 0;
		for(Account account : accounts) {
			if (account.getAccountId() > maxId) {
				maxId = account.getAccountId();
			}
		}
		return maxId;
	}
	
	public static long getMaxPersonId(List<Person> persons) {
		long maxId = 0;
		for(Person person : persons) {
			if (person.getPersonId() > maxId) {
				maxId = person.getPersonId();
			}
		}
		return maxId;
	}

	public static long getMaxBankId(List<Bank> banks) {
		long maxId = 0;
		for(Bank bank : banks) {
			if (bank.getBankId() > maxId) {
				maxId = bank.getBankId();
			}
		}
		return maxId;
	}

}
