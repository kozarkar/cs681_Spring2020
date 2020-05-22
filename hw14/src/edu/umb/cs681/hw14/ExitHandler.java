package edu.umb.cs681.hw14;

public class ExitHandler implements Runnable {
    private AdmissionMonitor ctr = new AdmissionMonitor();

    @Override
    public void run() {
        ctr.exit();
    }
}
