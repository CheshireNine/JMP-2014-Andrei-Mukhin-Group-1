package com.epam.concurrency.menu.action;

import java.util.List;

import com.epam.concurrency.SelectionForm;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.services.BankService;
import com.epam.concurrency.utils.ConsoleManager;


public class SelectBankAction implements IMenuItemAction {
	private static BankService service = new BankService();

	private SelectionForm form;

	public SelectBankAction(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Bank> banks = service.getList();
		ShowBanksAction.printBanks(banks);
		
		int banknum = 0;
		while(banknum == 0) {
			try {
				banknum = Integer.parseInt(ConsoleManager
						.getInput("Input bank num"));
			} catch(NumberFormatException ex) {
				ConsoleManager.writeLine("Should be a valid number");
			}
		}
		
		form.setBankId(banks.get(banknum - 1).getBankId());
	}


}
