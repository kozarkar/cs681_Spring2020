package edu.umb.cs681.hw17;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StockQuoteObservable extends Observable{

	protected HashMap<String, Float> hMap;

	public StockQuoteObservable() {
		obvs = new ConcurrentLinkedQueue<Observer>();
		hMap = new HashMap<>();
	}
	
	public void changeQuote(String t,float q) {
		this.hMap.put(t,q);
		setChanged();
		System.out.println(getClass().getSimpleName());
		
		System.out.println(hMap);
	}
	
	public HashMap<String, Float> getHM() {
		return hMap;
	}
	
}