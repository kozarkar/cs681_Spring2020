package edu.umb.cs681.hw05;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends CancellablePrimeGenerator implements Runnable {

	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	ReentrantLock reLock = new ReentrantLock();


	public void setDone() {
		reLock.lock();
		try {
			finished = true;			
		} finally {
			reLock.unlock();
		}
	}
	
	public void generatePrimes() {
		for (long n = from; n <= to; n++) {
			reLock.lock();
			try {
				if (finished) {
					System.out.println("Stopped...");
					this.primes.clear();
					break;
				}
				if (isPrime(n)) {
					this.primes.add(n);
				}
			} finally {
				reLock.unlock();
			}
		}
	}
	
	public void run() {
		generatePrimes();
	}
	
	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
		Thread thread = new Thread(gen);
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n------------------------------------------------------------------");
		System.out.println("Calculating [using RunnableCancellablePrimeGeneratorPrimeGenerator]");
		System.out.println("------------------------------------------------------------------");
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}

