package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {

	private static AtomicReference<ConcurrentSingleton> instance = null;

	private ConcurrentSingleton() { };

	public static AtomicReference<ConcurrentSingleton> getInstance() {
		if (instance == null){
			instance = new AtomicReference<ConcurrentSingleton>( new ConcurrentSingleton() );
		}
		return instance;
	}

}