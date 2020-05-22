package edu.umb.cs681.hw17;

public class Main {


	public static void main(String[] args) {
		
	DJIAQuoteObservable dqob = new DJIAQuoteObservable();
	
	System.out.println("Initially :");
	System.out.println("----------------------");
	dqob.changeQuote(250);
	
	dqob.addObserver((Observable o, Object obj)->{System.out.println("observer A : " + obj);});
	dqob.addObserver((Observable o, Object obj)->{System.out.println("observer B : " + obj);});
	System.out.println("\nObservers: " + dqob.countObservers());
	System.out.println("\n\nAfter changing quote :");
	System.out.println("----------------------");
	dqob.changeQuote(210);
	
	dqob.notifyObservers(dqob.getQuote());
	System.out.println();
	
	StockQuoteObservable sqob = new StockQuoteObservable();
	
	System.out.println("Initially:");
	System.out.println("----------------------");
	sqob.changeQuote("AAPL", 100);
	
	sqob.addObserver((Observable o, Object obj)->{System.out.println("observer A : " + obj);} );
	sqob.addObserver((Observable o, Object obj)->{System.out.println("observer B : " + obj);} );
	System.out.println("\nObservers " + sqob.countObservers());
	System.out.println("\n\nAfter changing quote :");
	System.out.println("----------------------");
	sqob.changeQuote("GOOG", 200);
	
	sqob.notifyObservers(sqob.getHM());
	
	sqob.changeQuote("GOOG", 110);
	sqob.notifyObservers(sqob.getHM());
	
}
}

