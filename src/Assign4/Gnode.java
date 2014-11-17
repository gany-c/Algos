package Assign4;

import java.util.ArrayList;
import java.util.List;

public class Gnode implements Comparable<Gnode>{
	
	public int value = 0;
	
	public List<Gnode> outGoing = new ArrayList<Gnode>();
	
	public List<Gnode> incoming = new ArrayList<Gnode>();
	
	public int finishingTime = -1;
	
	public int leader = -1;
	
	boolean explored = false;

	public int compareTo(Gnode o) {
		
		if(o.finishingTime == this.finishingTime)
			return 0;
		else if(o.finishingTime < this.finishingTime)
			return 1;
		else 
			return -1;
	}

}
