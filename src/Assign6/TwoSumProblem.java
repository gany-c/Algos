package Assign6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import util.FileReader;

public class TwoSumProblem {
	
	public int sum(String fileName) throws FileNotFoundException{
		
		try 
		{
			Scanner scanner = new Scanner(new File(fileName));
			
			ConcurrentSkipListSet<Long> numSet = new ConcurrentSkipListSet<Long>();
			Set<Long> sumSet =  new HashSet<Long>();
			int i = 0;

			while(scanner.hasNextLong())
			{
				Long num = scanner.nextLong();
			     if(!numSet.contains(num))
			     {
			    	 for(Long prev:numSet)
			    	 {
			    		 Long sum = prev + num;
			    		 sumSet.add(sum);
			    	 }
			    	 numSet.add(num);
			     }
			    	 
			     i++;
			}
			
			System.out.println("number of lines = "+i);
			
					
			
			return sumSet.size();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}		

		
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		String userDir = System.getProperty("user.dir");
		
		System.out.println("user Dir "+userDir);
		String filePath = userDir + "/resources/algo1-programming_prob-2sum.txt";
		System.out.println("filePath "+filePath);
		
		TwoSumProblem algo = new TwoSumProblem();
		int sum = algo.sum(filePath);
		
		System.out.println("output = "+sum);
		

	}

}
