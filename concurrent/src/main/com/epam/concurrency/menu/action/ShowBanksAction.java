package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.BankComparator;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.services.BankService;
import com.epam.concurrency.utils.ConsoleManager;


public class ShowBanksAction implements IMenuItemAction {
	private BankService bankService;

	public ShowBanksAction() {
	}

	@Override
	public void execute() {
		List<Bank> banks = bankService.getList();
		Collections.sort(banks, new BankComparator());
		printBanks(banks);
	}

	public static void printBanks(List<Bank> banks){
		if (banks != null) {
			ConsoleManager.writeLine("#\tBankId\tName");
			int number = 1;
			for(Bank bank : banks) {
				ConsoleManager.writeLine(number
						+ "\t"+ bank.getBankId()
						+ "\t" + bank.getName());
			}
		} else {
			ConsoleManager.writeLine("<Empty bank list>");
		}
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

}
