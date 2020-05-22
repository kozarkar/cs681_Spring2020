package edu.umb.cs681.hw17;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

abstract class Observable {

    protected ConcurrentLinkedQueue<Observer> obvs;
    
    AtomicBoolean c = new AtomicBoolean(false);
    
	ReentrantLock rL = new ReentrantLock();
	
    protected void addObserver(Observer obv) {
    	rL.lock();
		try {
			if(!obvs.contains(obv)) {
	    		obvs.add(obv);
	    		}
	    	c.set(true);			
		} finally {
			rL.unlock();
		}
    }

    protected void deleteObserver(Observer obv) {
    	rL.lock();
		try {
			obvs.remove(obv);			
		} finally {
			rL.unlock();
		}
    }

    protected void deleteObserver() {
    	rL.lock();
		try {
			obvs.removeAll(obvs);			
		} finally {
			rL.unlock();
		}
    }

    protected void setChanged() {
    	c.set(true);
    }
    /*
    public boolean hasChanged() {
    	return c;
    }
  */  
    
    protected void clearChanged() {
    	c.set(false);
    }
    
    public int countObservers() {
    	return obvs.size();
    }

    public void notifyObservers(Object arg) {
    	System.out.println("\n" + getClass().getSimpleName() + "\nNotify Observers: ");
    	rL.lock();
		if(c.get()) {
			
			obvs.forEach((Observer obvs) -> obvs.update(this, arg));
			
			clearChanged();
		}
		rL.unlock();
    }
}