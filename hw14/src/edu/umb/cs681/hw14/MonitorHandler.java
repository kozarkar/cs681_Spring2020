package edu.umb.cs681.hw14;



public class MonitorHandler implements Runnable  {
    private AdmissionMonitor ctr = new AdmissionMonitor();

    @Override
    public void run() {
        System.out.println(ctr.countCurrentVisitors());
    }
}
