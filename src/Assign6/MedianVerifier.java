package Assign6;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MedianVerifier {
	
	public static void main(String[] args) {
		
		try 
		{
			List<Integer> numList = util.FileReader.readFromFile("/Users/Ramanan/Documents/workspace-sts-3.3.0.RELEASE/Algos/resources/Median.txt");
			
			long sum = 0;
			int count = 0;
			
			List<Integer> mm = new ArrayList<Integer>();
			for(int val:numList){
				System.out.println("Value from file = "+val);
				mm.add(val);
				Collections.sort(mm);
				
				int median = getMedian(mm);
				System.out.println("New median = "+median);
				sum = sum +median;
				System.out.println("------------------------------------- ");
				//Thread.sleep(20);
				
//				if(count >=12)
//					break;
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

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int getMedian(List<Integer> mm) {

		if(mm == null || mm.isEmpty())
			return 0;
		
		
		int size = mm.size();
		
		if(size%2 == 0)
			return mm.get(size/2 -1);
		else
			return mm.get((size+1)/2-1);
	}	

}
