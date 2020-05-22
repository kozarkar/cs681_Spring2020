package edu.umb.cs681.hw16;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
    private final AccessCounter ctr=AccessCounter.getInstance();
    private final ReentrantLock rLock =new ReentrantLock();
    private boolean done = false;
    private static final List<Path> RANDOM_FILE_PATHS = Arrays.asList(Paths.get("A.html"),Paths.get("B.html"));

    public void setDone() {
        rLock.lock();
        this.done = true;
        rLock.unlock();
    }


    public ReentrantLock getLock() 
    {
        return rLock;
    }

    @Override
    public void run() {
        while (true) {
            rLock.lock();
            try {
                if (done) {
                    System.out.println("Process stopped!");
                    break;
                }
            }finally {
                rLock.unlock();
            }

            Path path = RANDOM_FILE_PATHS.get(Math.abs(new Random().nextInt())% RANDOM_FILE_PATHS.size());
            ctr.increment(path);
            System.out.println(path + " = " + ctr.getCount(path));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted "+e.getMessage());
            }
        }
    }
}
