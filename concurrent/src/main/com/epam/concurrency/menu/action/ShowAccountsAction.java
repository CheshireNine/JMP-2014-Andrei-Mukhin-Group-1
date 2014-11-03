package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.AccountComparator;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.utils.ConsoleManager;

public class ShowAccountsAction implements IMenuItemAction {
	private AccountService accountService;

	public ShowAccountsAction() {
	}

	@Override
	public void execute() {
		List<Account> accounts = accountService.getList();
		Collections.sort(accounts, new AccountComparator());
		printAccounts(accounts);
	}
	
	public static void printAccounts(List<Account> accounts) {
		
		if (accounts != null) {
			ConsoleManager.writeLine("#\tAccountId\tOwner\tAmount\tCurrency\tRate");
			int number = 1;
			for(Account account : accounts) {
				if (account.getOwner() == null) {
					ConsoleManager.writeLine(number
							+ "\t" + account.getAccountId()
							+ "\t" + "<No owner>"
							+ "\t" + (account.getAmount() )
							+ "\t" + account.getCurrency().getName()
							+ "\t" + account.getCurrency().getRate());
				} else {
					ConsoleManager.writeLine(number
							+ "\t" + account.getAccountId()
							+ "\t" + account.getOwner().getFirstName()
							+ "\t" + account.getOwner().getLastName()
							+ "\t" + (account.getAmount() )
							+ "\t" + account.getCurrency().getName()
							+ "\t" + account.getCurrency().getRate());
				}
				number++;
			}
		} else {
			ConsoleManager.writeLine("<Empty account list>");
		}
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
