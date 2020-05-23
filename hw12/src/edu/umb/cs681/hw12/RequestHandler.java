package edu.umb.cs681.hw12;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {
	private ReentrantLock lock;
	private boolean done = false;
	private static ArrayList<Path> filesList = new ArrayList<Path>();

	public RequestHandler() {
		lock = new ReentrantLock();

		filesList.add(Paths.get("a.html"));
		filesList.add(Paths.get("b.html"));
	
	}

	@Override
	public void run() {
	
		Random random = new Random();
		while (true) {
			lock.lock();
			try {
				if (done) {
					break;
				}
			} finally {
				lock.unlock();
			}

			int index = random.nextInt(filesList.size());
			Path filePath = filesList.get(index);

			AccessCounter.getInstance().increment(filePath);
			

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				continue;
			}
		}

	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}


}