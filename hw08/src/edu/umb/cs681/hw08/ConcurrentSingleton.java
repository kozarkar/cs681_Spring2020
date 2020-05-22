package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {

	private static ConcurrentSingleton instance = null;
	private static ReentrantLock reLock = new ReentrantLock();
	private static int counter = 0;	
	
	private ConcurrentSingleton()
	{
		if (this.getClass() == ConcurrentSingleton.class) 
		{
			reLock.lock();
			try {
				counter++;
				} 
			finally {
					reLock.unlock();
					}
		}
		
	};
	
	
		public static ConcurrentSingleton getInstance(){
			
			reLock.lock();
			
			try {
				
				if(instance==null){
					instance = new ConcurrentSingleton();
					
					System.out.println("Instance created by a thread\n ");
				}
				
				System.out.println(Thread.currentThread().getName());
				System.out.println("Instance running is: "+instance+ "\n");
				
			} finally {
				reLock.unlock();
						}
			return instance;
		}
		
		

		public static String getCounter() {
			// TODO Auto-generated method stub
			return "Total number of instances created: "+ counter;
			
		}
	
}

