package Assign5;

public class Edge implements Comparable<Edge> {
	
	Vertex head = null;
	Vertex tail = null;
	int weight = 0;
	
	public int compareTo(Edge o) {
		
		int myCost = this.head.distanceFromSource + weight;
		int otherCost = this.head.distanceFromSource + weight;
		
		return myCost - otherCost;
	}
	
	


}
