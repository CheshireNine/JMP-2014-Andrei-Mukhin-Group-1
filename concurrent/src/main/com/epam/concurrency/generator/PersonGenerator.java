package com.epam.concurrency.generator;

import java.util.concurrent.CountDownLatch;

import com.epam.concurrency.services.PersonService;

public class PersonGenerator implements Runnable {
	
	private PersonService service;

	private final CountDownLatch latch;

	public PersonGenerator(CountDownLatch latch) {
		this.latch = latch;
	}

	public void setService(PersonService service) {
		this.service = service;
	}

	@Override
	public void run() {
		int count = service.getList().size();
		while(!Thread.currentThread().isInterrupted()) {
			if(count >= 5) {
				latch.countDown();
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			}
			String firstName = "FirstName" + count;
			String lastName = "LastName" + count;
			if(service.addPerson(firstName, lastName) != 0) {
				count++;
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

	}

}
