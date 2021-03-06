package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.CurrencyComparator;
import com.epam.concurrency.form.SelectionForm;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.services.CurrencyService;
import com.epam.concurrency.utils.ConsoleManager;

public class ExchangeCurrenciesAction implements IMenuItemAction {
	private CurrencyService currencyService;
	private AccountService accountService;

	private SelectionForm form;

	public ExchangeCurrenciesAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExchangeCurrenciesAction(SelectionForm form) {
		this.form = form;
	}

	public void setForm(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Currency> currencies = currencyService.getList();
		Collections.sort(currencies, new CurrencyComparator());
		Account currentAccount = accountService.fetchById(form.getAccountId());
		ShowCurrenciesAction.printCurrencies(currencies);
		int currencyNum = 0;
		while(currencyNum == 0) {
			try {
				currencyNum = Integer.parseInt(ConsoleManager
						.getInput("Input currency num you want to exchange"));
			} catch(NumberFormatException ex) {
				ConsoleManager.writeLine("Should be a valid number");
			}
		}
		Currency selectedCurrency = currencies.get(currencyNum - 1);
		long oldAmount = currentAccount.getAmount();
		float oldRate = currentAccount.getCurrency().getRate();
		int oldPrecision = 10^currentAccount.getCurrency().getPrecision();
		float newRate = selectedCurrency.getRate();
		int newPrecision = 10^selectedCurrency.getPrecision();
		long amount = Math.round((((oldAmount * oldRate) / oldPrecision) / newRate) * newPrecision);
		currentAccount.setAmount(amount);
		currentAccount.setCurrency(selectedCurrency);
		accountService.editAccount(currentAccount);
		ShowAccountAction.printAccount(currentAccount);
	}

	public static void printCurrencies(List<Currency> currencies){
		if (currencies != null) {
			int number = 1;
			ConsoleManager.writeLine("#\tCurrencyId\tName\tRate");
			for(Currency currency : currencies) {
				ConsoleManager.writeLine(number
						+ "\t" + currency.getCurrencyId()
						+ "\t" + currency.getName()
						+ "\t" + currency.getRate());
				number++;
			}
		} else {
			ConsoleManager.writeLine("<Empty currency list>");
		}
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
