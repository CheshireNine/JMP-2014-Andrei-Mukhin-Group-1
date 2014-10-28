package com.epam.concurrency.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.epam.concurrency.utils.ConsoleManager;

public class ThreadsInterruptionService implements Runnable {

	private ExecutorService service;
	
	public ThreadsInterruptionService(ExecutorService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.shutdownNow();
	    try {
			if (!service.awaitTermination(100, TimeUnit.MILLISECONDS)) {
			    System.out.println("Still waiting...");
			    System.exit(0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		ConsoleManager.writeLine("Finishing threads");
	}

}
