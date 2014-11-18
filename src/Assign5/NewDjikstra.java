package Assign5;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

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
