package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class DJIAQuoteObservable extends Observable {	
	
	protected float qt;
	ReentrantLock reLock = new ReentrantLock();
	
	public DJIAQuoteObservable() 
	{
		obvs = new LinkedList<Observer>();
	}

	public float getQuote() 
	{
		return qt;
	}
	public void changeQuote(float q) {
		reLock.lock();
		try {
    	this.qt = q;
    	this.setChanged();
    	System.out.print(getClass().getSimpleName() +": ");
    	System.out.println(qt);
		}finally {
			reLock.unlock();
		}
    }
}