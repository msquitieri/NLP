public class Range {
	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public int getStart() { return start; }
	public int getEnd() { return end; }
	public boolean contains(int val) { return (val >= start && val < end); }
	
	public boolean equals(Object obj) {
		if (obj instanceof Range) {
			Range range = (Range) obj;
			return (range.getStart() == start && range.getEnd() == end);
		}
		
		return false;
	}
	
	public int hashCode() {
		String hash = "Start: " + start + " End: " + end;
		return hash.hashCode();
	}
	
	public String toString() { return "[" + start + ", " + end + ")"; }
		
	private int start;
	private int end;
}