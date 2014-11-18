package Assign5;

public class Edge implements Comparable<Edge> {
	
	Vertex head = null;
	Vertex tail = null;
	int weight = 0;
	
	
	
	@Override
	public String toString() {
		return "Edge [tail=" + tail.label + ", weight=" + weight + "]";
	}



	public int compareTo(Edge o) {
		
		int myCost = this.head.distanceFromSource + weight;
		int otherCost = o.head.distanceFromSource + o.weight;
		
		return myCost - otherCost;
	}
	
	


}
