package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
	
	public static List<Integer> readFromFile(String fileName) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File(fileName));
		List<Integer> output = new ArrayList<Integer>();
		int i = 0;
		while(scanner.hasNextInt())
		{
		     output.add(scanner.nextInt());
		}
		
		return output;
	}
	
	public static Map<Integer,List<Integer>> readAdjacencyList(String fileName) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File(fileName));
		Map<Integer,List<Integer>> output = new LinkedHashMap<Integer,List<Integer>>();
		
		
		while(scanner.hasNextLine())
		{
		     String fileLine = scanner.nextLine();
		     
		     String[] fileVals = fileLine.split("\\s+");
		     
		     if(fileVals.length > 1)
		     {
		    	 
		    	 int key = Integer.parseInt(fileVals[0]);
		    	 List<Integer> edges = new LinkedList<Integer>();
		    	 	 
		    	 for(int i = 1;i<fileVals.length;i++)
		    	 {
		    		 edges.add(Integer.parseInt(fileVals[i]));
		    	 }
		    	 
		    	 output.put(key,edges);
		    	 
		     }
		}
		
		return output;
	}	

}
