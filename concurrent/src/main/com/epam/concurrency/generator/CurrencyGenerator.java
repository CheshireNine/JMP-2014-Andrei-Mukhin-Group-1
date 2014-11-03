package com.epam.concurrency.generator;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.epam.concurrency.model.Bank;
import com.epam.concurrency.services.BankService;
import com.epam.concurrency.services.CurrencyService;

public class CurrencyGenerator implements Runnable {

	private final CountDownLatch latch;
	private static final int DIGITS_NUM = 5;
	private static final int RANGE = 5;

	private CurrencyService currencyService;
	private BankService bankService;

	public CurrencyGenerator(CountDownLatch latch) {
		this.latch = latch;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	@Override
	public void run() {
		int count = currencyService.getList().size();
		Random generator = new Random(RANGE);
		while(!Thread.currentThread().isInterrupted()) {

			if( count >= 5 ) {
				latch.countDown();
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			}
			List<Bank> banks = bankService.getList();
			if(banks.size() > 0) {
				long bankId = banks.get(generator.nextInt(banks.size()) % banks.size())
						.getBankId();
				String name = "Currency" + count;
				float rate = generator.nextFloat() * DIGITS_NUM;
				rate = rate < 0 ? rate*(-1) : rate;
				int precision = generator.nextInt(DIGITS_NUM);
				
				if(currencyService.addCurrency(bankId, name, rate, precision) != 0) {
					count++;
				}
				
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

	}

}
