/**
 * 
 */
package com.epam.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Petr_Tsiatnev
 *
 */
public class CurrenciesManagement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);

		ExecutorService service = Executors.newFixedThreadPool(5);

		service.execute(new AccountGenerator(latch));
		service.execute(new CurrencyGenerator(latch));
		service.execute(new BankGenerator(latch));
		service.execute(new PersonGenerator(latch));
		service.execute(new ClientService(latch));
		service.shutdown();

	}

}
