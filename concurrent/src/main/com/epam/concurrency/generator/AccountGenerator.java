package com.epam.concurrency.generator;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.epam.concurrency.model.Account;
import com.epam.concurrency.model.Bank;
import com.epam.concurrency.model.Currency;
import com.epam.concurrency.model.Person;
import com.epam.concurrency.services.AccountService;
import com.epam.concurrency.services.BankService;
import com.epam.concurrency.services.CurrencyService;
import com.epam.concurrency.services.PersonService;

public class AccountGenerator implements Runnable {
	private static final int AMOUNT_RANGE = 100000;

	private CurrencyService currencyService;
	private AccountService accountService;
	private BankService bankService;
	private PersonService personService;
	
	private final CountDownLatch latch;

	public AccountGenerator(CountDownLatch latch) {
		this.latch = latch;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@Override
	public void run() {

		int count = accountService.getList().size();
		Random generator = new Random();
		while(!Thread.currentThread().isInterrupted()) {
			if(count >= 5) {
				latch.countDown();
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			}
			List<Bank> banks = bankService.getList();
			int size = banks.size();
			if(size > 0) {
				List<Currency> currencies = currencyService.fetchByBankId(banks
						.get(generator.nextInt(size) % size).getBankId());
				size = currencies.size();
				if(size > 0) {
					Currency currency = currencies.get(generator.nextInt(size) % size);
					long amount = generator.nextLong() % AMOUNT_RANGE;
					amount = (amount < 0) ? amount * (-1) : amount;
					long accountId = accountService.addAccount(currency, amount);
					count ++;
					List<Person> persons = personService.getList();
					size = persons.size();
					if (size > 0) {
						 Account currentAccount = accountService.fetchById(accountId);
						 if(currentAccount.getAccountId() > 0) {
							 currentAccount.setOwner(persons.get(generator.nextInt(size) % size));
							 accountService.editAccount(currentAccount);
						 }
					}
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
