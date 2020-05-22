package edu.umb.cs681.hw02;

import java.util.ArrayList;

public class Car {
	
	private int year, price, mileage;
	private String model;
	
	public Car(String model, int year, int price, int mileage) {
		this.model = model;
		this.year = year;
		this.price = price;
		this.mileage = mileage;
	}
	
	public String getModel() {
		return model;
	}
	public int getYear() {
		return year;
	}
	public int getPrice() {
		return price;
	}
	public int getMileage() {
		return mileage;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Car> cars = new ArrayList<Car>();

		Car c1 = new Car("Toyota Camry", 2020, 24425, 29);
		Car c2 = new Car("Ford Explorer", 2016, 26000, 19);
		Car c3 = new Car("Mercedes Benz C300", 2016, 39875, 25);
		Car c4 = new Car("Honda Accord", 2020, 24020, 30);

		cars.add(c1);
		cars.add(c2);
		cars.add(c3);
		cars.add(c4);
		
		//min() with reduce()
		
		Integer minp = cars.stream().map((Car car)-> car.getPrice()).reduce(0, (result, carp)->
				{
					if(result==0) 
						return carp;
					else if(carp < result) 
						return carp;
					else 
						return result;
				} );
		System.out.println("\nLowest price for a car is : "  +minp);		
		
		//max() with reduce()
		Integer maxp = cars.stream().map((Car car)-> car.getPrice()).reduce(0, (result, carp)->
				{
					if(result==0) 
						return carp;
					else if(carp > result) 
						return carp;
					else return result;
				} );
		System.out.println("\nHighest price for a car is :  "  + maxp);		

		//count() with reduce()
		Integer numb = cars.stream().map( (Car car)-> car.getModel() ).reduce(0,(result, carMaker)-> ++result,(finalResult,intermidiateResult)->finalResult);	
		System.out.println("\nNumber of cars are : " + numb);
	}

}
