package edu.umb.cs681.hw19;

import java.util.ArrayList;

public class Car {

	private int year, price,mileage;
	private int c;
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
	public  int getMileage() {
		return mileage;
	}
	
	//new data field getter and setter
	public void setDominationCount(int c) {
		this.c = c;
	}

	public int getDominationCount() {
		return c;
	}
	
	public static void main(String args[]) 
    {
		ArrayList<Car> cars = new ArrayList<Car>();
		
		System.out.println("Using Map Reduce");
		System.out.println("---");
		Car c1 = new Car("Toyota Camry", 2020, 24425, 29);
		Car c2 = new Car("Ford Explorer", 2016, 26000, 19);
		Car c3 = new Car("Mercedes Benz C300", 2016, 39875, 25);
		Car c4 = new Car("Honda Accord", 2020, 24020, 30);
		Car c5 = new Car("Tesla", 2019, 28000, 26);

		cars.add(c1);
		cars.add(c2);
		cars.add(c3);
		cars.add(c4);
		cars.add(c5);

		
		cars.forEach(e -> System.out.println("Model: "+ e.getModel()+", Price: "+e.getPrice()+", Mileage: "+e.getMileage()+", Year: "+e.getYear()));
		
		Integer price = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> 
				result==0 ? carPrice : (carPrice < result ? carPrice : result),
				(finalResult,intermediateResult)->
				finalResult == 0 ? intermediateResult : (intermediateResult < finalResult ? intermediateResult : finalResult)
			);
		System.out.println("\nCheapest car price [using map-reduce] : "+price);
		
		Integer mxprice = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> 
					result==0 ? carPrice : (carPrice > result ? carPrice : result),
					(finalResult,intermediateResult)->
					finalResult == 0 ? intermediateResult : (intermediateResult > finalResult ? intermediateResult : finalResult)
				);
		 System.out.println("Most expensive car price [using map-reduce] : "+mxprice);
		
		Integer c = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> ++result , 
						(finalResult,intermediateResult)->
							finalResult + intermediateResult
						);
		System.out.println("Number of cars are  "+c);
		
		Integer model = cars.stream()
						.parallel()
						.map((Car car)->car.getModel())
						.reduce(0,(result, carModel)->
						result==0 ? carModel.length() : (carModel.length() < result ? carModel.length() : result),
								(finalResult,intermediateResult)->
								finalResult == 0 ? intermediateResult : (intermediateResult < finalResult ? intermediateResult : finalResult)
							);
		System.out.println("Smallest car model name is of "+model + " letters");
		
	}
	


}
