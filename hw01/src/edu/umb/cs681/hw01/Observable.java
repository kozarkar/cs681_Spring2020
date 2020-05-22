package edu.umb.cs681.hw01;

import java.util.LinkedList;


abstract class Observable {

    protected boolean c;
    protected LinkedList<Observer> obvs;
    
    protected void addObserver(Observer obv) {
    	if(!obvs.contains(obv)) {
    		obvs.add(obv);
    		}
    	c = false;
    }
    protected void deleteObserver(Observer obv) {
    	obvs.remove(obv);
    }

    protected void deleteObserver() {
    	obvs.removeAll(obvs);
    }

    protected void setChanged() {
    	c = true;
    }

    public boolean hasChanged() {
    	return c;
    }
    
    protected void clearChanged() {
    	c = false;
    }
    
    public int countObservers() {
    	return obvs.size();
    }

    public void notifyObservers(Object arg) {
    	System.out.println("\n" + getClass().getSimpleName() + " Notify Observers: ");
		if(hasChanged()) 
		{
			obvs.forEach((Observer obvs) -> obvs.update(this, arg));
			//clear
			clearChanged();
		}
    }
}
