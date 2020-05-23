package edu.umb.cs681.hw21;

import java.util.LinkedList;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args){
    	
    	ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );
        ExecutorCompletionService<LinkedList<Long>> cs = new ExecutorCompletionService<>(executor);
        AccessCounter ac = AccessCounter.getInstance();
        RequestHandler rh = new RequestHandler(ac);
       
       for(int i=0;i<10;i++) 
       {
       	executor.execute(rh);
       }
       executor.shutdown();
       try {
       	executor.awaitTermination(500, TimeUnit.MILLISECONDS);
       }catch (Exception e) {
			
       	e.printStackTrace();
		}
       
    }
       
        }
    



