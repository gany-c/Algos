package Assign4;

import java.util.ArrayList;
import java.util.List;

public class Gnode {
	
	public int value = 0;
	
	public List<Gnode> outGoing = new ArrayList<Gnode>();
	
	public List<Gnode> incoming = new ArrayList<Gnode>();
	
	public int order = -1;
	
	public int order2 = -1;

}
