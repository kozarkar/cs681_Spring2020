package edu.umb.cs681.hw14;

public class EntranceHandler implements Runnable  {
    private AdmissionMonitor ctr = new AdmissionMonitor();

    @Override
    public void run() {
        ctr.enter();
    }
}