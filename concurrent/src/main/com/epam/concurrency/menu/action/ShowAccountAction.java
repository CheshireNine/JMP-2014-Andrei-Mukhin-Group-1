package com.epam.concurrency.menu.action;

import com.epam.concurrency.SelectionForm;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.utils.ConsoleManager;

public class ShowAccountAction implements IMenuItemAction {
	private static AccountService service = new AccountService();

	private SelectionForm form;

	public ShowAccountAction(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		Account account = service.fetchById(form.getAccountId());
		printAccount(account);
		
	}
	
	public static void printAccount(Account account) {
		if (account.getAccountId() != 0) {
			if (account.getOwner() == null) {
				ConsoleManager.writeLine(account.getAccountId()
						+ "\t" + "<No owner>"
						+ "\t" + account.getCurrency().getName()
						+ "\t" + (account.getCurrency().getRate() * account.getAmount()));
			} else {
				ConsoleManager.writeLine(account.getAccountId()
						+ "\t" + account.getOwner().getFirstName()
						+ "\t" + account.getOwner().getLastName()
						+ "\t" + account.getCurrency().getName()
						+ "\t" + (account.getCurrency().getRate() * account.getAmount()));
			}

		} else {
			ConsoleManager.writeLine("<Empty account>");
		}
	}

}
