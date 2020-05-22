package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable{
	private boolean finished = false;
	private File f1;
	ReentrantLock lock = new ReentrantLock();
	
	public Editor(File file) {
		f1 = file;
	}
	
	public void run() {
		lock.lock();
		while(true) {
			try {
				if(finished) {
					System.out.println("Editing..");
					break;
				}
				f1.change();
				f1.save();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
				}finally {
					lock.unlock();
					}
			}
		}
	
	public void setDone() {
		finished = true;
	}
	
}
