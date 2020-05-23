package edu.umb.cs681.hw12;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private HashMap<java.nio.file.Path, Integer> ctr;
    private ReentrantLock reLock;
    private static ReentrantLock SingletonLock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {
        ctr = new HashMap<java.nio.file.Path, Integer>();
        reLock = new ReentrantLock();
    }

    
    public static AccessCounter getInstance() {
        SingletonLock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
        } finally {
            SingletonLock.unlock();
        }
		return instance;
    }

	public HashMap<Path, Integer> getAccessCounter() {
		return ctr;
	}

    
    public void increment(Path path) {
        reLock.lock();
        try {
            if (ctr.containsKey(path)) {
                ctr.put(path, ctr.get(path) + 1);
            } else {
                ctr.put(path, 1);
            }
        } finally {
            System.out.println(Thread.currentThread().getName()  + "::" + " incrementing counter for file : " + path + ". counter : " + ctr.get(path));
            reLock.unlock();
        }
    }

    public int getCount(Path path) {
        reLock.lock();
        try {
        	int count = 0;
            if (ctr.containsKey(path)) {
                count = ctr.get(path);
            } else {
                count = 0;
            }

            System.out.println(Thread.currentThread().getName()  + " getCount " + path + " count " + ctr.get(path));
			return count;
        } finally {
            reLock.unlock();
        }
    }


}