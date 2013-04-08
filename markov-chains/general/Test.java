import java.util.*;

public class Test {
	static public void main(String [] args) {
		System.out.println("HELLO!");
		TreeSet set = new TreeSet();

		for (int i=0; i<=500; i+=100) {
			Range range = new Range(i, i+100);
			set.add(range);
		}

		System.out.println(set);
		System.out.println(set.contains(23));
	}
}
