package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.CurrencyComparator;
import com.epam.concurrency.form.SelectionForm;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.services.CurrencyService;
import com.epam.concurrency.utils.ConsoleManager;

public class AddAccountAction implements IMenuItemAction {
	private static CurrencyService currencyService = new CurrencyService();
	private static AccountService accountService = new AccountService();

	private SelectionForm form;
	
	public AddAccountAction(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		//TODO validation needed
		List<Currency> currencies = currencyService.fetchByBankId(form.getBankId());
		Collections.sort(currencies, new CurrencyComparator());
		ShowCurrenciesAction.printCurrencies(currencies);
		int currencyNum = 0;

		while(currencyNum == 0) {
			try {
				currencyNum = Integer.parseInt(ConsoleManager
						.getInput("Please input a preferred currency number"));
			} catch(NumberFormatException ex) {
				ConsoleManager.writeLine("Should be a valid number");
			}
		}

		Currency currency = currencies.get(currencyNum - 1);
		long amount = 0;
		while(amount == 0) {
			try {
				amount = Long.parseLong(
						ConsoleManager.getInput("Amount: "));
			} catch(NumberFormatException ex) {
				ConsoleManager.writeLine("Amount should be a valid number");
			}
		}

		long accountId = accountService.addAccount(currency, amount);
		form.setAccountId(accountId);
		ShowAccountAction.printAccount(accountService.fetchById(accountId));

	}

}
