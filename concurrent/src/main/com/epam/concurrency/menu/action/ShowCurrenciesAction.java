package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.CurrencyComparator;
import com.epam.concurrency.form.SelectionForm;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.services.CurrencyService;
import com.epam.concurrency.utils.ConsoleManager;

public class ShowCurrenciesAction implements IMenuItemAction {
	private static CurrencyService service = new CurrencyService();

	private SelectionForm form;

	public ShowCurrenciesAction() {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Currency> currencies = service.getList();
		Collections.sort(currencies, new CurrencyComparator());
		printCurrencies(currencies);
	}

	public static void printCurrencies(List<Currency> currencies){
		if (currencies != null) {
			int number = 1;
			ConsoleManager.writeLine("#\tCurrencyId\tName\tPrecision\tRate");
			for(Currency currency : currencies) {
				ConsoleManager.writeLine(number
						+ "\t" + currency.getCurrencyId()
						+ "\t" + currency.getName()
						+ "\t" + currency.getPrecision()
						+ "\t" + currency.getRate());
				number++;
			}
		} else {
			ConsoleManager.writeLine("<Empty currency list>");
		}
	}

}
