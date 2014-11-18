package Assign5;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	
	int label = 0;
	int distanceFromSource = Integer.MAX_VALUE;
	List<Edge> outgoing = new ArrayList<Edge>();
	boolean explored = false;

}
