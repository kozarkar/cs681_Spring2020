package edu.umb.cs681.hw08;

public class Main {

	public static void main(String[] args) {
		//threads
		
		Thread thread1 = new Thread(()->{ConcurrentSingleton.getInstance();}, "Thread1 ....");
		thread1.start();
		
		Thread thread2 = new Thread(()->{ConcurrentSingleton.getInstance();},"Thread2 ....");
		thread2.start();
		
		Thread thread3 = new Thread(()->{ConcurrentSingleton.getInstance();},"Thread3 ....");
		thread3.start();
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(ConcurrentSingleton.getCounter());
		
		
	}

}
