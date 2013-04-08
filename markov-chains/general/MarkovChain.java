import java.util.*;
import java.io.*;

public class MarkovChain {
	static public void main(String [] args) {
		MarkovChain mc = new MarkovChain();

		mc.add("to be or not to be, that is the question");
		//mc.add("to what not hey question okay");

		try {
			//mc.add(new File("./Hamlet.txt"));
		} catch (Exception e) { System.err.println(e); }

		/*for (int i=0; i<10; i++)
			System.out.println(mc.build("Ophelia"));*/

		System.out.println(mc);
	}
        public void add(String str) {
		chainify(str);
	}
	public void add(File file) throws IOException {
		chainify(getFileContents(file));
	}
	public String build(String start) {
		if (start.endsWith(".")) return start;

		ChainLink cl = map.get(start);
		return start + " " + build(cl.next());
	}
	private String getFileContents(File file) throws IOException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder str = new StringBuilder();
		String temp;

		temp = br.readLine();
		while (temp != null) {
			str.append(temp + " ");
			temp = br.readLine();
		}
		br.close();

		return str.toString();
	}
	private void chainify(String str) {
        	ChainLink cl;
        	String prev, current;
        	Scanner scan = new Scanner(str);
        	
		if (scan.hasNext()) { 
			prev = scan.next();
		
			while(scan.hasNext()) {
				current = scan.next();
				cl = (ChainLink) map.get(prev);
				if (cl == null) cl = new ChainLink(prev);
				cl.addNext(current);
				map.put(prev, cl);
				prev = current;
			}
		}
     		scan.close();
        }
	public String toString() { return map.toString(); } 
        private HashMap<String, ChainLink> map = new HashMap<String, ChainLink>();
}
