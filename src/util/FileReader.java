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
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class FileReader {
	
	public static ConcurrentSkipListSet<Long> readNumbersFromFile(String fileName) throws FileNotFoundException{
		

		try 
		{
			Scanner scanner = new Scanner(new File(fileName));
			
			ConcurrentSkipListSet<Long> output = new ConcurrentSkipListSet<Long>();
			int i = 0;

			while(scanner.hasNextLong())
			{
			     output.add(scanner.nextLong());
			     i++;
			}
			
			System.out.println("number of lines = "+i);
			
			return output;			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}	
	
	public static List<Integer> readFromFile(String fileName) throws FileNotFoundException{
		

		try 
		{
			Scanner scanner = new Scanner(new File(fileName));
			
			List<Integer> output = new ArrayList<Integer>();
			int i = 0;

			while(scanner.hasNextInt())
			{
			     output.add(scanner.nextInt());
			     i++;
			}
			
			System.out.println("number of lines = "+i);
			
			return output;			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}
	
	public static int[][] readAdjacencyMatrix(String fileName,int length) throws FileNotFoundException{
		
		int[][] output = new int[length][length];
		
		Scanner scanner = new Scanner(new File(fileName));
		
		while(scanner.hasNextLine())
		{
		     String fileLine = scanner.nextLine();
		     
		     String[] fileVals = fileLine.split("\\s+");
		     
		     if(fileVals.length > 1)
		     {
		    	 int xval = Integer.parseInt(fileVals[0])-1;
		    	 
		    	 for(int i = 1;i<fileVals.length;i++)
		    	 {
		    		String[] twotokens = fileVals[i].split(",");
		    		int yval = Integer.parseInt(twotokens[0])-1;
		    		
		    		int weight = Integer.parseInt(twotokens[1]);
		    		
		    		output[xval][yval] = weight;
		    	 }		    	 
		     }
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
