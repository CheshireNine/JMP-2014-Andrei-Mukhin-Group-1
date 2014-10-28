package com.epam.concurrency.services;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import com.epam.concurrency.menu.MenuManager;

public class ClientService implements Runnable {

	private CountDownLatch latch;
	private CyclicBarrier barrier;

	public ClientService(CountDownLatch latch, CyclicBarrier barrier) {
		this.latch = latch;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			latch.await();
			MenuManager.createMenu().execute();
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
