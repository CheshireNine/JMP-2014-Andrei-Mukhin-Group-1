package com.epam.concurrency;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.epam.concurrency.model.Bank;
import com.epam.concurrency.services.BankService;

public class BankGenerator implements Runnable {
	private static final BankService service = new BankService();
	private final CountDownLatch latch;

	public BankGenerator(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {

		List<Bank> banks = service.getList();
		if(banks.size() == 0) {
			service.addBank("Main bank");
		}
		latch.countDown();
	}

}
