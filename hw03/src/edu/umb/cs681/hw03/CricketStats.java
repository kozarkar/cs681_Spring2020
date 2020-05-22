package edu.umb.cs681.hw03;

//import java.awt.List;
import java.util.ArrayList;
//import java.util.stream.*;


public class CricketStats {
	
	private String Name;
	private int Matches, Runs, Fifties, Hundreds, StrikeRate;
	
		public CricketStats(String Name, int Matches, int Runs, int Fifties, int Hundreds, int StrikeRate) {
			this.Name = Name;
			this.Matches = Matches;
			this.Runs = Runs;
			this.Fifties = Fifties;
			this.Hundreds = Hundreds;
			this.StrikeRate = StrikeRate;
		}
		
		public String getName() {
			return Name;
		}
		public int getMatches() {
			return Matches;
		}
		public int getRuns() {
			return Runs;
		}
		public int getFifties() {
			return Fifties;
		}
		public int getHundreds() {
			return Hundreds;
		}
		public int getStrikeRate() {
			return StrikeRate;
		}
	
	
	public static void main(String[] args) {
		
		ArrayList<CricketStats> cricketers = new ArrayList<CricketStats>();
		
		CricketStats c1 = new CricketStats("Sachin Tendulkar", 463, 18426, 96, 49, 86);
		CricketStats c2 = new CricketStats("Brian Lara", 299, 10405, 63, 19, 79);
		CricketStats c3 = new CricketStats("Virat Kohli", 248, 11867, 58, 43, 93);
		CricketStats c4 = new CricketStats("AB De Villiers", 228, 9577, 53, 25, 101);
		CricketStats c5 = new CricketStats("Rohit Sharma", 224, 9115, 43, 29, 88);
		
		cricketers.add(c1);
		cricketers.add(c2);
		cricketers.add(c3);
		cricketers.add(c4);
		cricketers.add(c5);
		
		cricketers.forEach(e -> System.out.println("Name: "+ e.getName()+", Matches: "+e.getMatches()+", Runs: "+e.getRuns()+", 50s: "+e.getFifties()+", 100s: "+e.getHundreds()+", StrikeRate: "+e.getStrikeRate()));
		System.out.println("\n------------------------");
		System.out.println("\nKey Stats :");
		System.out.println("------------------------");
		
		Integer minr = cricketers.stream().map((CricketStats cricketer)-> cricketer.getRuns()).reduce(0, (result, carp)->
		{
			if(result==0) 
				return carp;
			else if(carp < result) 
				return carp;
			else 
				return result;
		} );
		System.out.println("\nLowest number of runs scored by a player are : "  +minr);
		
		Integer maxr = cricketers.stream().map((CricketStats cricketer)-> cricketer.getRuns()).reduce(0, (result, carp)->
		{
			if(result==0) 
				return carp;
			else if(carp > result) 
				return carp;
			else return result;
		} );
		System.out.println("\nHighest number of runs scored by a player are :  "  + maxr);	
		
		Integer minm = cricketers.stream().map((CricketStats cricketer)-> cricketer.getMatches()).reduce(0, (result, carp)->
		{
			if(result==0) 
				return carp;
			else if(carp < result) 
				return carp;
			else 
				return result;
		} );
		System.out.println("\nLowest number of matches played by a player are : "  +minm);
		
		Integer maxm = cricketers.stream().map((CricketStats cricketer)-> cricketer.getMatches()).reduce(0, (result, carp)->
		{
			if(result==0) 
				return carp;
			else if(carp > result) 
				return carp;
			else return result;
		} );
		System.out.println("\nHighest number of matches played by a player are :  "  + maxm);

	}
}
