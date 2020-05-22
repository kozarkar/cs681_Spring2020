package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
    	
        System.out.println("Access Counter");
		System.out.println("---");
		List<Thread> threads=new ArrayList<>();
        List<RequestHandler> rh=new ArrayList<>();
        for(int i = 0; i< 7; i++){
            RequestHandler requestHandler=new RequestHandler();
            Thread thread = new Thread(requestHandler);
            threads.add(thread);
            rh.add(requestHandler);
            thread.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted "+e.getMessage());
        }

        for(int i = 0; i< 7; i++){
            rh.get(i).getLock().lock();
            rh.get(i).setDone();
            rh.get(i).getLock().unlock();
        }

        for(int i = 0; i< 7; i++){
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted while joining "+e.getMessage());
            }
        }
    }


}
