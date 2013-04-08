import java.util.*;
import java.util.Map.Entry;

class ChainLink {
        public ChainLink(String value) {
                this.value = value;
                next = new HashMap<String, Integer>();
                total = 0;
        }
        public String toString() {
                return "{ val: " + value + ", map: " + next + ", total: " + total + " }";
        }
        public int hashCode() { return this.value.hashCode(); }
        public String getValue() { return this.value; }
        public int getMapSize() { return next.size(); }
        public boolean equals(Object obj) {
    		if (obj instanceof ChainLink) {
    			ChainLink tmp = (ChainLink) obj;
    			return (this.value.equals(tmp.value));
    		}
    		return false;
    	} 
        public void addNext(String str) {
        	Integer count = next.get(str);
        	if (count == null) count = 0;
        	count++;
        	total++;
        	next.put(str, count);
        }
        
        private WeightedDie<String> getWeightedDie() {
        	WeightedDie<String> die = new WeightedDie<String>();
        	
        	for (String str : next.keySet())
        		die.add(str, next.get(str));
        	
        	return die;
        }
        
        public String next() {
        	//WeightedDie<String> die = getWeightedDie();
        	return getWeightedDie().roll();
        	
        	
        	/****** PURELY RANDOM ******
        	Object [] arr = next.keySet().toArray();
        	Random ran = new Random();
        	return (String) arr[ran.nextInt(arr.length)];
        	*/
        	
        	/****** RETURNS LARGEST OCCURENCE ******
        	Set<Entry<String, Integer>> set = next.entrySet();
        	String maxstr = "";
        	Integer maxcount = 0;
        	for (Map.Entry<String, Integer> e : set) if (maxcount < e.getValue()) { 
        		maxstr = e.getKey();
        		maxcount = e.getValue();
        	}
        
        	return maxstr;*/
        }
       
        //public Vector<LinkEntry> prev;
        private String value;
        private HashMap<String, Integer> next;
        private Integer total;
}
