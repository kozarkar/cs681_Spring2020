package edu.umb.cs681.hw01;

import java.util.HashMap;
import java.util.LinkedList;

public class StockQuoteObservable extends Observable{

	protected HashMap<String, Float> hMap;

	public StockQuoteObservable() {
		obvs = new LinkedList<Observer>();
		hMap = new HashMap<>();
	}
	
	public void changeQuote(String t,float q) {
		this.hMap.put(t,q);
		setChanged();
		System.out.println(getClass().getSimpleName());
		System.out.println(hMap);
	}
	
	public HashMap<String, Float> getHashMap() {
		return hMap;
	}
	
}