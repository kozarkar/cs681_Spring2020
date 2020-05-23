package edu.umb.cs681.hw21;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static final ReentrantLock rL = new ReentrantLock();

    private static AccessCounter instance = null;
    private static final ConcurrentHashMap<Path, Integer> concCount = new ConcurrentHashMap<>();

    private AccessCounter() {
    }

    public static AccessCounter getInstance() {
        rL.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        } finally {
            rL.unlock();
        }
    }

    
    //eliminating client locking
    public void increment(java.nio.file.Path path) {
        concCount.compute(path, (p, i) -> i == null ? 1 : ++i);
    }

    public int getCount(java.nio.file.Path path) {
        if(concCount.containsKey(path))
            return concCount.get(path);
        return 0;
    }
}