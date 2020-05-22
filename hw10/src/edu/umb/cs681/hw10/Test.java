package edu.umb.cs681.hw10;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<Thread> thr = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread( () -> {
				System.out.println( ConcurrentSingleton.getInstance());
			} );

			thr.add(thread);
			thread.start();
		}

		for (Thread t : thr) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}

}