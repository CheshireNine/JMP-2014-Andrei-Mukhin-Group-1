package com.epam.concurrency.comparator;

import java.util.Comparator;

import com.epam.concurrency.model.Account;

public final class AccountComparator implements Comparator<Account> {

	public AccountComparator() {
	}

	@Override
	public int compare(Account o1, Account o2) {
		return o1.getAccountId() < o2.getAccountId() ? -1 :
			o1.getAccountId() > o2.getAccountId() ? 1 : 0;
	}

}
