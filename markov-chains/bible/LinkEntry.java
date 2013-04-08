class LinkEntry {
	public LinkEntry(String str) {
		this.str = str;
		this.count = 0;
	}
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof LinkEntry) {
			LinkEntry tmp = (LinkEntry) obj;
			result = (this.str.equals(tmp.str));
		}
		return result;
	}
	public String toString() { return "{ " + this.str + " : " + this.count + " }"; }
	public void increment() { this.count++; }
	//public void decrement() { this.count--; }
	public int hashCode() { return this.str.hashCode(); }
	
	public String str;
	public Integer count;
	
}