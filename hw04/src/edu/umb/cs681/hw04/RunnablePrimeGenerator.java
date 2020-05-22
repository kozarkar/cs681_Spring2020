package edu.umb.cs681.hw04;


public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {

		System.out.println("Running.....");
		
		//Threads
		//Thread 1
		RunnablePrimeGenerator g = new RunnablePrimeGenerator(1, 2000000);
		Thread t = new Thread(g);
		
		long timerStart1 = System.currentTimeMillis();
		t.start();
		try {
			t.join();
		} 
		catch (InterruptedException e) {	
		}
		
		long timerEnd1 = System.currentTimeMillis();
	    long totalTime1 = timerEnd1 - timerStart1;
	    
	    g.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		
		long primeN = g.getPrimes().size();
		System.out.println( "\n" + primeN + " prime numbers were found.");
		

	    System.out.println("1 running Thread total time: " + totalTime1 + " ms");

	    
		//Thread 2, two threads
		RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1, 1000000);
		RunnablePrimeGenerator g2 = new RunnablePrimeGenerator(1000001, 2000000);
		Thread t1 = new Thread(g1);
		Thread t2 = new Thread(g2);
		
		long timerStart2 = System.currentTimeMillis();
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} 
		catch (InterruptedException e) {}
		
		long timerEnd2 = System.currentTimeMillis();
	    long totalTime2 = timerEnd2 - timerStart2;
	    System.out.println("2 running Threads total time: " + totalTime2 + " ms");   
	    
		//Thread 4, four threads
		RunnablePrimeGenerator g4a = new RunnablePrimeGenerator(1, 500000);
		RunnablePrimeGenerator g4b = new RunnablePrimeGenerator(500001, 1000000);
		RunnablePrimeGenerator g4c = new RunnablePrimeGenerator(1000001, 1500000);
		RunnablePrimeGenerator g4d = new RunnablePrimeGenerator(1500001, 2000000);
		Thread t4a = new Thread(g4a);
		Thread t4b = new Thread(g4b);
		Thread t4c = new Thread(g4c);
		Thread t4d = new Thread(g4d);
		
		long timerStart4 = System.currentTimeMillis();
		t4a.start();
		t4b.start();
		t4c.start();
		t4d.start();
		try {
			t4a.join();
			t4b.join();
			t4c.join();
			t4d.join();
		} catch (InterruptedException e) {}
		
		long timerEnd4 = System.currentTimeMillis();
	    long totalTime4 = timerEnd4 - timerStart4;
		System.out.println("4 running Threads total time: " + totalTime4 + " ms");
	    
		//Thread 8, eight threads
		RunnablePrimeGenerator g8a = new RunnablePrimeGenerator(1, 250000);
		RunnablePrimeGenerator g8b = new RunnablePrimeGenerator(250001, 500000);
		RunnablePrimeGenerator g8c = new RunnablePrimeGenerator(500001, 750000);
		RunnablePrimeGenerator g8d = new RunnablePrimeGenerator(750001, 1000000);
		RunnablePrimeGenerator g8e = new RunnablePrimeGenerator(1000001,1250000);
		RunnablePrimeGenerator g8f = new RunnablePrimeGenerator(1250001,1500000);
		RunnablePrimeGenerator g8g = new RunnablePrimeGenerator(1500001,1750000);
		RunnablePrimeGenerator g8h = new RunnablePrimeGenerator(1750001,2000000);
		Thread t8a = new Thread(g8a);
		Thread t8b = new Thread(g8b);
		Thread t8c = new Thread(g8c);
		Thread t8d = new Thread(g8d);
		Thread t8e = new Thread(g8e);
		Thread t8f = new Thread(g8f);
		Thread t8g = new Thread(g8g);
		Thread t8h = new Thread(g8h);
		
		long timerStart8 = System.currentTimeMillis();
		t8a.start();
		t8b.start();
		t8c.start();
		t8d.start();
		t8e.start();
		t8g.start();
		t8h.start();
		try {
			t8a.join();
			t8b.join();
			t8c.join();
			t8d.join();
			t8e.join();
			t8f.join();
			t8g.join();
			t8h.join();
		} catch (InterruptedException e) {}
		
		long timerEnd8 = System.currentTimeMillis();
	    long totalTime8 = timerEnd8 - timerStart8;
		System.out.println("8 running Threads total time: " + totalTime8 + " ms");
	    
		//Thread 16, 16 running threads
		RunnablePrimeGenerator g16a = new RunnablePrimeGenerator(1,      125000);
		RunnablePrimeGenerator g16b = new RunnablePrimeGenerator(125001, 250000);
		RunnablePrimeGenerator g16c = new RunnablePrimeGenerator(250001, 375000);
		RunnablePrimeGenerator g16d = new RunnablePrimeGenerator(375001, 500000);
		RunnablePrimeGenerator g16e = new RunnablePrimeGenerator(500001, 625000);
		RunnablePrimeGenerator g16f = new RunnablePrimeGenerator(625001, 750000);
		RunnablePrimeGenerator g16g = new RunnablePrimeGenerator(750001, 875000);
		RunnablePrimeGenerator g16h = new RunnablePrimeGenerator(875001,  1000000);
		RunnablePrimeGenerator g16i = new RunnablePrimeGenerator(1000001, 1125000);
		RunnablePrimeGenerator g16j = new RunnablePrimeGenerator(1125001,1250000);
		RunnablePrimeGenerator g16k = new RunnablePrimeGenerator(1250001,1375000);
		RunnablePrimeGenerator g16l = new RunnablePrimeGenerator(1375001, 1500000);
		RunnablePrimeGenerator g16m = new RunnablePrimeGenerator(1500001, 1625000);
		RunnablePrimeGenerator g16n = new RunnablePrimeGenerator(1625001, 1750000);
		RunnablePrimeGenerator g16o = new RunnablePrimeGenerator(1750001, 1875000);
		RunnablePrimeGenerator g16p = new RunnablePrimeGenerator(1875001, 2000000);
		
		Thread t16a = new Thread(g16a);
		Thread t16b = new Thread(g16b);
		Thread t16c = new Thread(g16c);
		Thread t16d = new Thread(g16d);
		Thread t16e = new Thread(g16e);
		Thread t16f = new Thread(g16f);
		Thread t16g = new Thread(g16g);
		Thread t16h = new Thread(g16h);
		Thread t16i = new Thread(g16i);
		Thread t16j = new Thread(g16j);
		Thread t16k = new Thread(g16k);
		Thread t16l = new Thread(g16l);
		Thread t16m = new Thread(g16m);
		Thread t16n = new Thread(g16n);
		Thread t16o = new Thread(g16o);
		Thread t16p = new Thread(g16p);
		long timerStart6 = System.currentTimeMillis();
		t16a.start();
		t16b.start();
		t16c.start();
		t16d.start();
		t16e.start();
		t16f.start();
		t16g.start();
		t16h.start();
		t16i.start();
		t16j.start();
		t16k.start();
		t16l.start();
		t16m.start();
		t16n.start();
		t16o.start();
		t16p.start();
		try {
			t16a.join();
			t16b.join();
			t16c.join();
			t16d.join();
			t16e.join();
			t16f.join();
			t16g.join();
			t16h.join();
			t16i.join();
			t16j.join();
			t16k.join();
			t16l.join();
			t16m.join();
			t16n.join();
			t16o.join();
			t16p.join();
		} catch (InterruptedException e) {}
		
		long endTimer6 = System.currentTimeMillis();
	    long totalTime16 = endTimer6 - timerStart6;
		System.out.println("16 running Threads total time: " + totalTime16 + " ms");

	}

}
