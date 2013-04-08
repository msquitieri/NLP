import java.util.Random;
import java.util.HashMap;
import java.util.Vector;

public class WeightedDie<TYPE> {
	public static void main(String[] args) {
		WeightedDie<String> die = new WeightedDie<String>();
		
		/*String[] strarr = {"hello", "goodbye"};
		for (String s: strarr)
			die.add(s, 50);*/
		
		die.add("hello", 1);
		die.add("goodbye", 1);
		
		int numHello, numGoodbye, numTrue, numFalse, numNull;
		numHello = numGoodbye = numTrue = numFalse = numNull = 0;
		Random rand = new Random();
		for (int i=0; i<1000000; i++) {
			String result = die.roll();
			if (result == null) numNull++;
			else if (result.equals("hello")) numHello++;
			else numGoodbye++;
			
			if (rand.nextBoolean()) numTrue++;
			else numFalse++;
		}
		System.out.println("Hello: " + numHello + "\nGoodbye: " + numGoodbye);
		System.out.println("Nulls: " + numNull);
		System.out.println("\nTrue: " + numTrue + "\nFalse: " + numFalse);
	}
	/*
	public WeightedDie(int totalWeight) {
		this.totalWeight = totalWeight;
	}*/
	public void add(TYPE obj, int weight) {
		Range range = new Range(totalWeight, totalWeight+weight);
		totalWeight += weight;
		map.put(range, obj);
		//System.out.println(map);
	}
	public TYPE roll() {
		Random rand = new Random();
		int randInt = rand.nextInt(totalWeight);
		//System.out.println(randInt);
		
		for (Range range : map.keySet()) {
			if (range.contains(randInt)) {
				return map.get(range);
			}
		}
		
		return null;
	}
	
	//int totalWeight;
	HashMap<Range, TYPE> map = new HashMap<Range, TYPE>();
	int totalWeight = 0;
	int startPos = 0;	
}
