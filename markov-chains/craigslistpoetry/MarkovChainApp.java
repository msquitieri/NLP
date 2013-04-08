import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class MarkovChainApp {
        public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        	boolean done = false;
        	//initChainsWithDB();
        	//initChainsWithFile(new File("./Hamlet.txt"));
        	//initChainsWithFile(new File("./bible.txt"));
        	initChainsWithDB(new File("./allLines.txt"));

        	
        	//System.out.println(map);
        	
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	
        	do {
        		System.out.print("Select a string: ");
        		try {
					String input = br.readLine();
					System.out.println();
					System.out.println(getSentenceStartingWith(input));
					System.out.println();
				} catch (Exception e) {
					System.err.println("String not found!");
				}
        	} while (!done);
        	
        	//for (int i=0; i<10; i++) getSentenceStartingWith("fuck");
        }
        
        static public void initChainsWithDB() throws FileNotFoundException, SQLException, ClassNotFoundException {
        	Date start = new Date();
        	ChainLink cl;
        	String prev, current;
        	
        	Class.forName ("com.mysql.jdbc.Driver");
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CraigslistPoetry", "root", "itygfmtad");
        	//ResultSet rs = conn.createStatement().executeQuery("SELECT line_text FROM poetry_lines ORDER BY rand() LIMIT 10000");
        	ResultSet rs = conn.createStatement().executeQuery("SELECT line_text FROM poetry_lines");

        	//Scanner scan = new Scanner(new File("./Hamlet.txt"));
        	
        	//while(rs.next()) System.out.println(rs.getString("line_text"));

        	while (rs.next()) {
        		String line = rs.getString("line_text");
        		line = line.replaceAll("<br>", " ");

        		if (line.trim().equals("")) continue;
        		line += ".";
        		
        		Scanner scan = new Scanner(line);
        		//System.out.println(line);
        	
        		prev = scan.next();
        	
        		while(scan.hasNext()) {
        			current = scan.next();
        			cl = map.get(prev);
        			if (cl == null) cl = new ChainLink(prev);
        			cl.addNext(current);
        			map.put(prev, cl);
        			prev = current;
        		}
        		scan.close();
        	}
        	
        	rs.close();
        	conn.close();
        		
        	Date end = new Date();
        	System.out.println('\n' + (end.getTime()-start.getTime())+'\n');
        }
        static public void initChainsWithDB(File file) throws IOException {
        	Date start = new Date();
        	String line;
        	
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	
        	line = br.readLine();
        	while (line != null) {
        		chainify(line);
        		line = br.readLine();
        	}

        	Date end = new Date();
        	System.out.println('\n' + (end.getTime()-start.getTime())+'\n');
        }
        
        static private void chainify(String str) {
        	Scanner scan = new Scanner(str);
        	ChainLink cl;
        	String prev, current;
        	
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
        
        static public void initChainsWithFile(File file) throws FileNotFoundException {
        	Date start = new Date();
        	ChainLink cl;
        	String prev, current;
        	
        	Scanner scan = new Scanner(file);
    	
    		prev = scan.next();
    	
    		while(scan.hasNext()) {
    			current = scan.next();
    			cl = (ChainLink) map.get(prev);
    			if (cl == null) cl = new ChainLink(prev);
    			cl.addNext(current);
    			map.put(prev, cl);
    			prev = current;
    		}
    		scan.close();
    		
    		Date end = new Date();
        	System.out.println('\n' + (end.getTime()-start.getTime())+'\n');
        }
        
        static public String getSentenceStartingWith(String start) throws Exception {
        	//System.out.print(start + " ");
        	if (start.endsWith(".")) {
        		//System.out.println();
        		return start;
        	}
        
        	ChainLink cl = map.get(start);
        	return start + " " + getSentenceStartingWith(cl.next());
        }
    
        static public HashMap<String, ChainLink> map = new HashMap<String, ChainLink>();
}
