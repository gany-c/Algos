package Assign5;


import java.io.File;
import java.io.FileNotFoundException;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 
 * @author Ramanan
 *
 *In this programming problem you'll code up Dijkstra's shortest-path algorithm. 
Download the text file here. (Right click and save link as). 
The file contains an adjacency list representation of an undirected weighted graph with 200 vertices labeled 1 to 200. Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that edge. For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6. The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has length 8200. The rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths of the corresponding edges.

Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the source vertex, and to compute the shortest-path distances between 1 and every other vertex of the graph. If there is no path between a vertex v and vertex 1, we'll define the shortest-path distance between 1 and v to be 1000000. 

You should report the shortest-path distances to the following ten vertices, in order: 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a comma-separated string of integers. So if you find that all ten of these vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of reporting DOES MATTER, and the string should be in the same order in which the above ten vertices are given. Please type your answer in the space provided.

IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of Dijkstra's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing the heap-based version. Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.
 */

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
	
	public static Map<Integer, Vertex> loadGraph(String path) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File(path));
		Map<Integer,Vertex> output = new TreeMap<Integer,Vertex>();
		
		while(scanner.hasNextLine())
		{
		     String fileLine = scanner.nextLine();
		     //System.out.println("Line from file = "+fileLine);
		     
		     String[] fileVals = fileLine.split("\\s+");
		     
		     Vertex head = output.get(Integer.parseInt(fileVals[0]));		     
		     if(head == null)
		     {	 
		    	 head = new Vertex();
		    	 head.label = Integer.parseInt(fileVals[0]);
		    	 
		    	 output.put(head.label, head);
		     }	 
		     
		     for(int i=1;i<fileVals.length;i++)
		     {
		    	 String[] commaTokens = fileVals[i].split(",");
			     Vertex tail = output.get(Integer.parseInt(commaTokens[0]));		     
			     if(tail == null)
			     {	 
			    	 tail = new Vertex();
			    	 tail.label = Integer.parseInt(commaTokens[0]);
			    	 
			    	 output.put(tail.label, tail);
			     }	
			     
			     Edge e = new Edge();
			     e.weight = Integer.parseInt(commaTokens[1]);
			     
			     e.head = head;
			     e.tail = tail;
			     head.outgoing.add(e);		    	 
		    	 
		     }
		     
 
		     

		}
		scanner.close();
		return output;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/resources/dijkstraData.txt";
		
		Map<Integer, Vertex> graph = loadGraph(filePath);
		
		System.out.println("Size of graph = "+graph.size());
		

		
		Vertex start = graph.get(1);
		System.out.println("Starting vertex = ");
		System.out.println(start);
		
		NewDjikstra djikstra = new NewDjikstra();
		
		djikstra.findShortestPaths(start);
		
		int[] nodes = new int[]{7,37,59,82,99,115,133,165,188,197};
		
		System.out.println();
		
		for(int node:nodes){
			
			System.out.print(graph.get(node).distanceFromSource+",");
		}

	}

}
