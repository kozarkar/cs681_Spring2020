package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class Customer implements Runnable {
    private Address a;
    private Boolean finished = false;
    private ReentrantLock reLock = new ReentrantLock();
	

    public void setDone(){
        reLock.lock();
        try{
        finished = true;
        }
        finally{
        reLock.unlock();
        }
    }
    public Customer(Address add){
        a = add;
    }
    public void setAddress(Address add){
    	reLock.lock();
    	try {
    		a = add;
    	}
    	finally {
    		reLock.unlock();
    	}
        
    }

    public Address getAddress(){
            return a;
    }
    


    @Override
    public void run() {
        this.setAddress(a);
        System.out.println("Customer's Address : "+this.getAddress());
    }
}