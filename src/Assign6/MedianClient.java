package Assign6;

import java.io.FileNotFoundException;

import java.util.List;

import javax.naming.directory.InvalidAttributesException;

public class MedianClient {
	
	/**
	 * The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap applications). The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file, the kth median mk is defined as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among x1,…,xk; if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)

In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). That is, you should compute (m1+m2+m3+⋯+m10000)mod10000.

OPTIONAL EXERCISE: Compare the performance achieved by heap-based and search-tree-based implementations of the algorithm.
	 * @param args
	 */

	public static void main(String[] args) {
		
		try 
		{
			List<Integer> numList = util.FileReader.readFromFile("/Users/Ramanan/Documents/workspace-sts-3.3.0.RELEASE/Algos/resources/Median.txt");
			
			long sum = 0;
			int count = 0;
			
			MedianMaintain mm = new MedianMaintain(numList.size());
			
			for(int val:numList)
			{
				System.out.println("Value from file = "+val);
				mm.insert(val);
//				mm.display();
				int median = mm.getMedian();
				System.out.println("New median = "+median);
				sum = sum + median;
				System.out.println("------------------------------------- ");
				
//				if(count >=12)
//				{					
//					break;
//				}
//		
//				count++;
			}
			
			System.out.println("sum  = "+sum);
			System.out.println("answer = "+sum%10000);
			
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		} 
		catch (InvalidAttributesException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
