package com.epam.concurrency;

import java.util.concurrent.CountDownLatch;

public class ClientService implements Runnable {

	private final CountDownLatch latch;

	public ClientService(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		MenuManager.createMenu().execute();
	}

}
