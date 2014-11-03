package com.epam.concurrency.menu.action;

import java.util.Collections;
import java.util.List;

import com.epam.concurrency.comparator.AccountComparator;
import com.epam.concurrency.form.SelectionForm;
import com.epam.concurrency.model.Account;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.utils.ConsoleManager;


public class SelectAccountAction implements IMenuItemAction {
	private AccountService accountService;

	private SelectionForm form;

	public SelectAccountAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectAccountAction(SelectionForm form) {
		this.form = form;
	}

	public void setForm(SelectionForm form) {
		this.form = form;
	}

	@Override
	public void execute() {
		List<Account> accounts = accountService.getList();
		Collections.sort(accounts, new AccountComparator());
		ShowAccountsAction.printAccounts(accounts);
		
		int accountNum = 0;
		while(accountNum == 0) {
			try {
				accountNum = Integer.parseInt(ConsoleManager
						.getInput("Input account num"));
			} catch(NumberFormatException ex) {
				ConsoleManager.writeLine("Should be a valid number");
			}
		}

		Account selectedAccount = accounts.get(accountNum - 1);
		form.setAccountId(selectedAccount.getAccountId());
		ShowAccountAction.printAccount(selectedAccount);
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}


}
