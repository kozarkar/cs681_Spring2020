package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class File {
	private boolean changed = false;
	ReentrantLock lock = new ReentrantLock();
	
	public File() {
		lock = new ReentrantLock();
	}
	
	public void change() {
		lock.lock();
		try {
			changed = true;
		} finally{
			lock.unlock();
		}
	}
	
	public void save() {
		lock.lock();
		try {
			if(!changed)
				return;
			else 
				System.out.println(System.currentTimeMillis());
			changed = false;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		//creating extra threads
		File f = new File();
		Thread t1 = new Thread(new Editor(f));
		Thread t2 = new Thread(new AutoSaver(f));
		for (int i = 0; i < 1; i++) {
			System.out.println("Running Thread-Safe Editor... ");
			t1.start();
			System.out.println("Running Thread-Safe Auotosaver... ");
			t2.start();
			}
		}
}