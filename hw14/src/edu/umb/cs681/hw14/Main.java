package edu.umb.cs681.hw14;


public class Main {
    public static void main(String[] args) {
        System.out.println("Admission Monitor System");
        System.out.println("--");
        for (int i = 1; i <= 10; i++) {
            Thread entryThread = new Thread(new EntranceHandler());
            entryThread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted "+e.getMessage());
        }
        new Thread(new MonitorHandler()).start();
        ExitHandler exitHandler = new ExitHandler();
        Thread exitThread = new Thread(new Thread(exitHandler));
        exitThread.start();

        try {
            exitThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted while joining "+e.getMessage());
        }

        new Thread(new MonitorHandler()).start();
    }
}