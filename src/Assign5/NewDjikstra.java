package Assign5;


import java.util.PriorityQueue;
import java.util.Queue;

public class NewDjikstra {
	
	public void findShortestPaths(Vertex source){
		
		source.distanceFromSource = 0;
		source.explored = true;
		
		Queue<Edge> frontier = new PriorityQueue<Edge>();
		frontier.addAll(source.outgoing);
		
		while(!frontier.isEmpty()){
			
			Edge e = frontier.poll();
			if(e.tail.explored)
				continue;
			else
			{
				Vertex selected = e.tail;
				
				selected.distanceFromSource = e.head.distanceFromSource + e.weight;
				selected.explored = true;
				frontier.addAll(selected.outgoing);
			}
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
