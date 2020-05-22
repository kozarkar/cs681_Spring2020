package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class AutoSaver implements Runnable{
	private boolean finished = false;
	private File f1;
	ReentrantLock reLock = new ReentrantLock();
	
	public AutoSaver(File file) {
		f1 = file;
	}
	
	public void run() {
		reLock.lock();
		while(true) {
			try {
				if(finished) {
					System.out.println("Autosaving..");
					break;
				}
				f1.save();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
				}finally {
					reLock.unlock();
					}
			}
	}
	
	public void setDone() {
		finished = true;
	}
	
}

