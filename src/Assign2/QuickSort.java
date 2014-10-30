package Assign2;


import java.io.FileNotFoundException;
import java.util.List;

public class QuickSort {
	
	public int sortAndCount(List<Integer> inList,int start, int end){
		
		if(start >=end)
			return 0;
		else
		{
			int retValue = end - start;
			
			int partIndex = medianOf3Partition(inList,start,end);
			

			retValue = retValue + sortAndCount(inList,start,partIndex-1);			
			retValue = retValue + sortAndCount(inList,partIndex+1,end);

			
			return retValue;
				
		}
		
	}
	


	private int partition(List<Integer> inList,int start,int end){
		
		
		
		int p = inList.get(start);
		int i = start + 1;
		
		for(int j = start + 1;j<=end;j++)
		{
			if(inList.get(j) < p)
			{
				swap(inList,i,j);
				i++;
			}		
			
		}
		
		swap(inList,start,i-1);
		
		return i-1;
		
	}	

	private int medianOf3Partition(List<Integer> inList,int start,int end){
		
		int middleIndex = (start + end)/2;
		
		int medianIndex = findMedian(inList,start,end,middleIndex);
		
		swap(inList,start,medianIndex);
		
		int p = inList.get(start);
		int i = start + 1;
		
		for(int j = start + 1;j<=end;j++)
		{
			if(inList.get(j) < p)
			{
				swap(inList,i,j);
				i++;
			}		
			
		}
		
		swap(inList,start,i-1);
		
		return i-1;
		
	}	
	
	private int findMedian(List<Integer> inList, int start, int end,
			int middleIndex) {
		
		int max = Math.max(inList.get(middleIndex),Math.max(inList.get(start), inList.get(end)));
		
		int min = Math.min(inList.get(middleIndex),Math.min(inList.get(start), inList.get(end)));
		
		if(inList.get(start)!=max && inList.get(start)!=min)
			return start;
		else if(inList.get(end)!=max && inList.get(end)!=min)
			return end;
		else
			return middleIndex;
		
	}



	private int endPartition(List<Integer> inList,int start,int end){
		
		swap(inList,start,end);
		
		int p = inList.get(start);
		int i = start + 1;
		
		for(int j = start + 1;j<=end;j++)
		{
			if(inList.get(j) < p)
			{
				swap(inList,i,j);
				i++;
			}		
			
		}
		
		swap(inList,start,i-1);
		
		return i-1;
		
	}

	private void swap(List<Integer> inList, int i, int j) {
		
		int temp = inList.get(i);
		inList.set(i, inList.get(j));
		inList.set(j,temp);
		
	}

	public static void main(String[] args) {
		
		System.out.println("QuickSort");
		
		try 
		{
			String userDir = System.getProperty("user.dir");
			String filePath = userDir + "/resources/QuickSort.txt";
			List<Integer> inList = util.FileReader.readFromFile(filePath);
			System.out.println(inList.size());
			
			
			QuickSort sorter = new QuickSort();
			int output = sorter.sortAndCount(inList, 0, inList.size()-1);
			
			
			boolean sorted = testSorted(inList);
			System.out.println("is the list sorted? = "+sorted);
			
			
			System.out.println("Output = "+output);


			
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static boolean testSorted(List<Integer> inList) {
		
		for(int i = 0;i <inList.size()-1;i++)
			if(inList.get(i)>inList.get(i+1))
				return false;
		
		return true;
	}

}
