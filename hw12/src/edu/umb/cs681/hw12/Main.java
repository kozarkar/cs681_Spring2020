package edu.umb.cs681.hw12;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		

		ArrayList<Thread> arraythread = new ArrayList<>();
		RequestHandler request = new RequestHandler();

		final int nThread = 20;

		for (int i = 0; i < nThread; i++) {
			Thread td = new Thread(request);
			arraythread.add(td);
			td.start();
		}

		arraythread.forEach((Thread th) -> {
			System.out.println("marking 'finished' for : "+th.getName());
			try {
				th.interrupt();
				request.setDone();
				th.join();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});

	}

}