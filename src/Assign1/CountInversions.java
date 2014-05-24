package Assign1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CountInversions {
	

	
	public static long bruteCount(List<Integer> input){
		
	   long outCount = 0;
	   
	   for(int i = 0; i < input.size()-1; i++)
	   {
		   int firstVal = input.get(i);
		   
		   for(int j = i+1; j < input.size();j++)
		   {
			   int secondVal = input.get(j);
			   
			   if (firstVal > secondVal)
				   outCount++;
		   }
		   
	   }
	   
	   
	   return outCount;
	}
	
	public static CountHolder divConCount(List<Integer> input,int start, int end){
		
		if(start == end)
			return new CountHolder(0,new int[]{input.get(start)});
		else
		{
			int mid = (start + end)/2;
			
			CountHolder left = divConCount(input,start,mid);
			CountHolder right = divConCount(input,mid+1,end);
			
			CountHolder output = mergeAndCountSplits(left.mergedList,right.mergedList);
			
			output.numSplitInv = output.numSplitInv + left.numSplitInv + right.numSplitInv;
			
			return output;
			
		}
		
	
		
	}
	
	private static CountHolder mergeAndCountSplits(int[] left,
			int[] right) {
		
		CountHolder output = new CountHolder(0,new int[left.length + right.length]);
		int i=0,j= 0,k = 0;
		
		while(i < left.length && j <right.length)
		{
			if(left[i] <= right[j])
				output.mergedList[k++] = left[i++];
			else
			{
				output.mergedList[k++] = right[j++];
				output.numSplitInv = output.numSplitInv + left.length - i;
			}
		}
		
		
		while (i < left.length)
				output.mergedList[k++] = left[i++];
		
		while(j < right.length)
			  output.mergedList[k++] = right[j++];
	
			
		
		return output;
	}

	public static void main(String[] args){
		System.out.println("hi");
		try 
		{
			List<Integer> numList = util.FileReader.readFromFile("/Users/Ramanan/Algorithms/Assignments/src/IntegerArray.txt");
			System.out.println("length of list = "+numList.size());
			long start = System.currentTimeMillis();
			long numInversions = bruteCount(numList);
			long middle = System.currentTimeMillis();			
			CountHolder holder = divConCount(numList,0,numList.size()-1);
			long end = System.currentTimeMillis();	
			
			System.out.println("Brute count answer = "+numInversions+", time taken = "+(middle -start));
			System.out.println("Divide and conauer = "+holder.numSplitInv+", time taken = "+(end - middle));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
