package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.BankComparator;
import com.epam.concurrency.form.SelectionForm;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.services.BankService;
import com.epam.concurrency.utils.ConsoleManager;


public class SelectBankAction implements IMenuItemAction {
	private BankService bankService;

	private SelectionForm form;

	public SelectBankAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectBankAction(SelectionForm form) {
		this.form = form;
	}

	public void setForm(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Bank> banks = bankService.getList();
		Collections.sort(banks, new BankComparator());
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

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}


}
