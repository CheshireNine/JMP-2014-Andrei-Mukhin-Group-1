package com.epam.concurrency.comparator;

import java.util.Comparator;

import com.epam.concurrency.model.Bank;

public class BankComparator implements Comparator<Bank> {

	public BankComparator() {
	}

	@Override
	public int compare(Bank o1, Bank o2) {
		return o1.getBankId() < o2.getBankId() ? -1 :
			o1.getBankId() > o2.getBankId() ? 1 : 0;
	}

}
