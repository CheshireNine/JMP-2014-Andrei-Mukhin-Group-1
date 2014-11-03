/**
 * 
 */
package com.epam.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

		ExecutorService service = Executors.newFixedThreadPool(5);
		CyclicBarrier barrier = new CyclicBarrier(1, new ThreadsInterruptionService(service));

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		service.execute((AccountGenerator)context.getBean("accountGenerator"));
		service.execute((CurrencyGenerator)context.getBean("currencyGenerator"));
		service.execute((BankGenerator)context.getBean("bankGenerator"));
		service.execute((PersonGenerator)context.getBean("personGenerator"));
		service.execute(new ClientService((CountDownLatch)context.getBean("latch"), barrier));
	}

}
