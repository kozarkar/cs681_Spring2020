package edu.umb.cs681.hw05;

public class CancellablePrimeGenerator extends PrimeGenerator{
	protected boolean finished = false;

	public CancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
		
	public void setDone(){
		finished = true;
	}

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// to stop
			if(finished){
				System.out.println("Stopped!");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
		}
	}
}