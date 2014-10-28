/**
 * 
 */
package com.epam.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.epam.concurrency.generator.AccountGenerator;
import com.epam.concurrency.generator.BankGenerator;
import com.epam.concurrency.generator.CurrencyGenerator;
import com.epam.concurrency.generator.PersonGenerator;
import com.epam.concurrency.services.ClientService;
import com.epam.concurrency.services.ThreadsInterruptionService;

/**
 * @author Petr_Tsiatnev
 *
 */
public class CurrenciesManagement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(4);

		ExecutorService service = Executors.newFixedThreadPool(5);
		CyclicBarrier barrier = new CyclicBarrier(1, new ThreadsInterruptionService(service));

		service.execute(new AccountGenerator(latch));
		service.execute(new CurrencyGenerator(latch));
		service.execute(new BankGenerator(latch));
		service.execute(new PersonGenerator(latch));
		service.execute(new ClientService(latch, barrier));

	}

}
