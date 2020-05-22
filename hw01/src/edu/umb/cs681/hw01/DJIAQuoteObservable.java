package edu.umb.cs681.hw01;

import java.util.LinkedList;


public class DJIAQuoteObservable extends Observable {	
	
	protected float qt;
	public DJIAQuoteObservable() 
	{
		obvs = new LinkedList<Observer>();
	}

	public float getQuote() 
	{
		return qt;
	}
	public void changeQuote(float q) {
    	this.qt = q;
    	this.setChanged();
    	System.out.print(getClass().getSimpleName() +": ");
    	System.out.println(qt);
    }
}