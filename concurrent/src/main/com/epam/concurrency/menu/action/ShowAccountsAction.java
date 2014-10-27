package com.epam.concurrency.menu.action;

import java.util.List;

import com.epam.concurrency.SelectionForm;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.utils.ConsoleManager;

public class ShowAccountsAction implements IMenuItemAction {
	private static AccountService service = new AccountService();

	private SelectionForm form;

	public ShowAccountsAction(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Account> accounts = service.getList();
		printAccounts(accounts);
	}
	
	public static void printAccounts(List<Account> accounts) {
		
		if (accounts != null) {
			ConsoleManager.writeLine("#\tAccountId\tOwner");
			int number = 1;
			for(Account account : accounts) {
				if (account.getOwner() == null) {
					ConsoleManager.writeLine(number
							+ "\t" + account.getAccountId()
							+ "\t" + "<No owner>");
				} else {
					ConsoleManager.writeLine(number
							+ "\t" + account.getAccountId()
							+ "\t" + account.getAmount()
							+ "\t" + account.getOwner().getFirstName()
							+ "\t"+account.getOwner().getLastName());
				}
				number++;
			}
		} else {
			ConsoleManager.writeLine("<Empty account list>");
		}
	}

}
