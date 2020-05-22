package edu.umb.cs681.hw17;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class DJIAQuoteObservable extends Observable {	
	
	protected float qt;
	
	ReentrantLock reLock = new ReentrantLock();
	
	public DJIAQuoteObservable() {
		obvs = new ConcurrentLinkedQueue<Observer>();
	}

	public float getQuote() {
		return qt;
	}
	public void changeQuote(float q) {
    	reLock.lock();
		try {
			this.qt = q;
	    	this.setChanged();
	    	System.out.println(getClass().getSimpleName() + ": "+ qt);		
		} finally {
			reLock.unlock();
		}

		
    	
    }
}