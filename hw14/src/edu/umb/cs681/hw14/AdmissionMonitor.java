package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionMonitor {
    private static int visitors = 0;
    private ReentrantLock reLock = new ReentrantLock();
    private Condition entry = reLock.newCondition();
    private Condition exit = reLock.newCondition();

    public int countCurrentVisitors() {
        reLock.lock();
        try {
            return visitors;
        } finally {
            reLock.unlock();
        }
    }

    public void enter() {
        reLock.lock();
        try {
            System.out.println("Entering...");
            while (visitors >= 5) {
                try {
                    System.out.println("Area full of visitors. Wait....");
                    entry.await();
                    //System.out.println("Executed");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            visitors++;
            exit.signalAll();
        } finally {
            reLock.unlock();
        }
    }

    public void exit() {
        reLock.lock();
        try {
            System.out.println("Exiting...");
            while (visitors <= 0) {
                try {
                    System.out.println(" No visitors to exit!!");
                    exit.await();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            visitors--;
            entry.signalAll();
        } finally {
            reLock.unlock();
        }
    }
}
