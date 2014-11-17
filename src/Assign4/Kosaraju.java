package Assign4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Ramanan
 * Download the text file here. Zipped version here. (Right click and save link as)
The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714. Every row indicates an edge, the vertex label in first column is the tail and the vertex label in second column is the head (recall the graph is directed, and the edges are directed from the first column vertex to the second column vertex). So for example, the 11th row looks liks : "2 47646". This just means that the vertex with label 2 has an outgoing edge to the vertex with label 47646

Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs), and to run this algorithm on the given graph. 

Output Format: You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes, separated by commas (avoid any spaces). So if your algorithm computes the sizes of the five largest SCCs to be 500, 400, 300, 200 and 100, then your answer should be "500,400,300,200,100". If your algorithm finds less than 5 SCCs, then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and 100, then your answer should be "400,300,100,0,0".

WARNING: This is the most challenging programming assignment of the course. Because of the size of the graph you may have to manage memory carefully. The best way to do this depends on your programming language and environment, and we strongly suggest that you exchange tips for doing this on the discussion forums.

Answer for Question 1
You entered:

Your Answer		Score	Explanation
434821,968,459,313,211	Correct	5.00	
Total		5.00 / 5.00	

 *
 */

public class Kosaraju {
	
	private static int t = 0;
	
	private void iDFS2(Gnode source) {

		if(source == null||source.explored)
			return;
		else
		{
			Stack<Gnode> stack = new Stack<Gnode>();
			stack.push(source);
			
			while(!stack.isEmpty())
			{
				
				Gnode node = stack.pop();
				
				if(!node.explored)
				{					
					node.explored = true;
					node.leader = source.value;
					
					for(Gnode desc:node.outGoing)
					{
						if(!desc.explored)
							stack.push(desc);
					}
				}
				
				
			}
			
		}
		
		
	}
	
	private void iDFS(Gnode source){
		
		if(source == null||source.explored)
			return;
		else
		{
			
			Stack<Gnode> stack = new Stack<Gnode>();
			stack.push(source);
			
			while(!stack.isEmpty()){
				
				Gnode node = stack.pop();
				
				if(!node.explored)
				{
					node.explored = true;
					
					stack.push(node);
					if(node.outGoing.isEmpty())
						node.finishingTime = t++;
					for(Gnode desc:node.outGoing)
					{
						if(!desc.explored)
							stack.push(desc);
					}					
				}
				else
				{
					node.finishingTime = t++;
				}

			}
			
			
		}
	}	
	
	@Deprecated
	private void dFS(Gnode source){
		
		if(source == null||source.explored)
			return;
		else
		{
			source.explored = true;
			//System.out.println(source.value);
			
			if(source.outGoing==null||source.outGoing.isEmpty())
				return;
			else
			{
				for(Gnode node:source.outGoing)
				 if(!node.explored)	
					dFS(node);
			}
				
			
		}
		
	}
	
	private void reverseGraph(List<Gnode> input){
		
		for(Gnode node:input){
		
			List<Gnode> temp = node.incoming;
			node.incoming = node.outGoing;
			node.outGoing = temp;
			
			node.explored = false;
			
		}
		
		
	}
	
	public static List<Gnode> loadGraph(String fileName) throws FileNotFoundException{
		
		 
		 Map<Integer,Gnode> output = new HashMap<Integer,Gnode>();
		 
			Scanner scanner = new Scanner(new File(fileName));

			while(scanner.hasNextLine())
			{
			     String fileLine = scanner.nextLine();
			     
			     String[] fileVals = fileLine.split("\\s+");
			     
			     if(fileVals.length > 1)
			     {
			    	 
			    	 int key = Integer.parseInt(fileVals[0]);
			    	 int val = Integer.parseInt(fileVals[1]);

			    	 Gnode head = output.get(key);
			    	 if(head == null)
			    	 {
			    		 head = new Gnode();
			    		 head.value = key;
			    		 output.put(key,head);
			    	 }
			    	 
			    	 Gnode tail = output.get(val);
			    	 if(tail == null)
			    	 {
			    		 tail = new Gnode();
			    		 tail.value =val;
			    		 output.put(val,tail);
			    	 }
			    	 
			    	 head.outGoing.add(tail);
			    	 tail.incoming.add(head);
			    		 
			     }
			}		 
		
	    
		scanner.close();
		
		List<Gnode> ouList = new ArrayList<Gnode>();
		for(Integer key: output.keySet())
		{
			ouList.add(output.get(key));
		}
		
		return ouList;
	}
	
	public void doublePass(List<Gnode> input){
		
		System.out.println(" Started Kosaraju");
		
		reverseGraph(input);
		
		System.out.println(" reversed graph");
		
		dFS_Loop1(input);
		
		System.out.println(" completed first dFS_loop graph");
		
		Collections.sort(input);
		
		Collections.reverse(input);	
		
		System.out.println(" completed sorting in descending order of time");
		
		reverseGraph(input);
		
		System.out.println(" completed second reversal");
		
		dFS_Loop2(input);
		
		System.out.println(" completed second dfs_loop");
		
		Map<Integer,Integer> countsMap = new HashMap<Integer,Integer>();
		
		for(Gnode node: input)
		{
			int leader = node.leader;
			
			if(countsMap.get(leader)==null)
				countsMap.put(leader, 1);
			else
				countsMap.put(leader, countsMap.get(leader)+1);
		}
		
		System.out.println(" created map of counts "+countsMap.size());
		
		List<Integer> countsList = new ArrayList<Integer>();
		
		for(Integer leader: countsMap.keySet())
		{
			countsList.add(countsMap.get(leader));
		}
		
		System.out.println(" created list of counts");
		
		Collections.sort(countsList);
		
		System.out.println("Top 5 largest strongly connected components are: ");
		
		for(int i = countsList.size()-1;i>countsList.size()-6;i--)
			System.out.print(countsList.get(i)+",");
		
	}
	
	private void dFS_Loop2(List<Gnode> input) {
		
		for(Gnode node:input){
			if(node.explored)
				continue;
			else
				iDFS2(node);
		}
		
	}



	private void dFS_Loop1(List<Gnode> input) {
		t = 0;
		
		for(Gnode node:input){
			if(node.explored)
				continue;
			else
				iDFS(node);
		}
	}

	public static void main(String[] args){
		
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/resources/SCC.txt";
		
		try 
		{
			List<Gnode> input = loadGraph(filePath);
			System.out.println(input.size());
			
			Kosaraju kosaraju = new Kosaraju();	
			
			kosaraju.doublePass(input);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
