package com.epam.concurrency.menu.action;

import java.util.List;

import com.epam.concurrency.SelectionForm;
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
		printCurrencies(currencies);
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

}
