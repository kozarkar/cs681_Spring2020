package edu.umb.cs681.hw15;

public class Main {


	public static void main(String[] args) {
		
	DJIAQuoteObservable dqob = new DJIAQuoteObservable();
	
	System.out.println("Initially :");
	dqob.changeQuote(250);
	
	dqob.addObserver((Observable o, Object obj)->{System.out.println("observer A : " + obj);});
	dqob.addObserver((Observable o, Object obj)->{System.out.println("observer B : " + obj);});
	System.out.println("\n Observers: " + dqob.countObservers());
	System.out.println("\n After changing quote :");
	dqob.changeQuote(210);
	
	dqob.notifyObservers(dqob.getQuote());
	System.out.println();
	
	StockQuoteObservable sqob = new StockQuoteObservable();
	
	System.out.println("Initially");
	sqob.changeQuote("AAPL", 100);
	
	sqob.addObserver((Observable o, Object obj)->{System.out.println("A : " + obj);} );
	sqob.addObserver((Observable o, Object obj)->{System.out.println("B : " + obj);} );
	System.out.println("\n Observers " + sqob.countObservers());
	System.out.println("\n|After Quote Change|");
	sqob.changeQuote("GOOG", 200);
	
	sqob.notifyObservers(sqob.getHashMap());
	
	sqob.changeQuote("GOOG", 110);
	sqob.notifyObservers(sqob.getHashMap());
	
}
}

