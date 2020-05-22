package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
		
		private ReentrantLock reLock = new ReentrantLock();
		private boolean finished = false;
		
		public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
			super(dividend, from, to);
			if (from >= 2 && to >= from) {
				this.from = from;
				this.to = to;
			} else {
				throw new RuntimeException(
					"from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
			}
		}
		
		public void setDone(){
			reLock.lock();
			try { 
				finished = true; 
			} finally {
				reLock.unlock(); 
			} 
		}
		
		public void generatePrimeFactors() {
			long divisor = from;
		    while(true){
				reLock.lock();
				try {
					if(finished || dividend == 1 || divisor > to)
						break;
					if( divisor > 2 && isEven(divisor)) {
						divisor++;
						continue;
					}
					if(dividend % divisor == 0) {
						factors.add(divisor);
						dividend /= divisor;
					} else {
						if(divisor==2) {divisor++;} 
						else {divisor += 2;}
			    	}
				} finally {
					reLock.unlock();
				}
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					System.out.println(e.toString());
					continue;
				}
			}
		}
		
		public void run() {
			generatePrimeFactors();
			reLock.lock();
			try {
				System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
			} finally {
				reLock.unlock();
			}
		}

		public static void main(String[] args) {
			
			System.out.println("Factorization of 36");
			RunnableCancellableInterruptiblePrimeFactorizer runnable = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long)Math.sqrt(36));
			Thread thread = new Thread(runnable);
			System.out.println("Thread #" + thread.getId() + 
				" performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Answer: " + runnable.getPrimeFactors() + "\n");
			
			
			
			// two-step thread termination
			System.out.println("Factorization of 36 with two-step thread termination");
			RunnableCancellableInterruptiblePrimeFactorizer runnable2 = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long)Math.sqrt(36));
			Thread thread2 = new Thread(runnable2);
			System.out.println("Thread #" + thread2.getId() + 
				" performs factorization in the range of " + runnable2.getFrom() + "->" + runnable2.getTo());
			thread2.start();
			runnable2.setDone();
			thread2.interrupt();
			try {
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Answer: " + runnable2.getPrimeFactors() + "\n");
		}
	
}
