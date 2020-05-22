package edu.umb.cs681.hw05;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.ArrayList;

public class PrimeGenerator {
	protected long from, to;
	protected List<Long> primes;

	public PrimeGenerator(long from, long to){
		if(from >= 1 && to >= from){
			this.primes = new ArrayList<Long>();
			this.from = from;
			this.to = to;
		}else{
			throw new RuntimeException("values wrong for from:" + from + " to:" + to);
		}
	}
	
	public List<Long> getPrimes(){ return primes; };
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}
	
	protected boolean isPrime(long n){
		if(n == 1){ 
			return false; 
		}
		 
		if( n > 2 && isEven(n) ){ 
			return false; 
		}
		
		long i;
		for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        if (i == 1){ 
			return true; 
		}
        else{ 
			return false; 
		}
	}

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ primes.add(n); }
        }
	}
	
	public static void main(String[] args) {
		
		
		// using generatePrimes()
		PrimeGenerator g = new PrimeGenerator(1, 100);
		g.generatePrimes();
		
		//System.out.println("----------------------------------------------");
		System.out.println("Prime numbers [using generatePrimes()] :");
		System.out.println("--");
		g.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + g.getPrimes().size() + " total prime numbers.");
		
		System.out.println("");

		//without using generatePrimes()
		PrimeGenerator g2 = new PrimeGenerator(1, 100);
		g2.primes = LongStream.rangeClosed(g.from, g.to).filter( (long n)->g.isPrime(n) ).boxed().collect(Collectors.toList());
		
		//System.out.println("------------------------------------------------");
		System.out.println("Prime numbers [without using generatePrimes()] :");
		System.out.println("--");
		g2.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + g2.getPrimes().size() + " total prime numbers");
	}
}
