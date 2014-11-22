package Assign6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
/**
 * 
 * @author Ramanan
 * Download the text file here. (Right click and save link as).

The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in the Week 6 lecture on hash table applications).
The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is your array of integers, with the ith row of the file specifying the ith entry of the array.

Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that there are distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)

Write your numeric answer (an integer between 0 and 20001) in the space provided.


OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example, you could compare performance under the chaining and open addressing approaches to resolving collisions.
 *
 */
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
