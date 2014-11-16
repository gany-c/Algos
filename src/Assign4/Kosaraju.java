package Assign4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Kosaraju {
	
	private static void iDFS(Gnode source){
		
		if(source == null||source.explored)
			return;
		else
		{
			
			Stack<Gnode> stack = new Stack<Gnode>();
			stack.push(source);
			
			while(!stack.isEmpty()){
				
				Gnode node = stack.pop();
				node.explored = true;
				System.out.println(node.value);
				
				for(Gnode desc:node.outGoing)
				{
					if(!desc.explored)
						stack.push(desc);
				}
			}
			
			
		}
	}	
	
	@Deprecated
	private static void dFS(Gnode source){
		
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
	
	private static void reverseGraph(Map<Integer,Gnode> input){
		
		for(Integer key: input.keySet()){
			
			Gnode node = input.get(key);
			
			List<Gnode> temp = node.incoming;
			node.incoming = node.outGoing;
			node.outGoing = temp;
			
			node.explored = false;
			
			
		}
		
		
	}
	
	public static Map<Integer,Gnode> loadGraph(String fileName) throws FileNotFoundException{
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
		return output;
	}
	public static void main(String[] args){
		
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/resources/SCC.txt";
		
		try 
		{
			Map<Integer,Gnode> input = loadGraph(filePath);
			System.out.println(input.size());
			
			reverseGraph(input);
			System.out.println(input.size());
			
			Gnode someRandomNode = input.entrySet().iterator().next().getValue();
			iDFS(someRandomNode);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
