package Assign6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class TwoSumProblem {
	
	public int sum(String fileName) throws FileNotFoundException, InterruptedException{
		
		try 
		{
			Scanner scanner = new Scanner(new File(fileName));
			
			ConcurrentSkipListSet<Long> numSet = new ConcurrentSkipListSet<Long>();
			Set<Integer> sumSet =  new TreeSet<Integer>();
			int j = 0;

			while(scanner.hasNextLong())
			{
				long num = scanner.nextLong();

			    numSet.add(num);
			     
			     if(j%1000 == 0)
			     {	
			    	 System.out.println("number of lines = "+j);
			    	
			     }	 
			     j++;
			}			
			
			
			scanner.close();		
			
			for(int i= -10000;i<=10000;i++)
			{
				for(long num:numSet)
				{
					if(numSet.contains(i-num))
						sumSet.add(i);
				}
				
			     if(i%100 == 0)
			     {	
			    	 System.out.println("number of sums = "+i);
			    	
			     }	
			}
			
			
			return sumSet.size();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}		

		
	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		String userDir = System.getProperty("user.dir");
		
		System.out.println("user Dir "+userDir);
		String filePath = userDir + "/resources/algo1-programming_prob-2sum.txt";
		System.out.println("filePath "+filePath);
		
		TwoSumProblem algo = new TwoSumProblem();
		int sum = algo.sum(filePath);
		
		System.out.println("output = "+sum);
		

	}

}
