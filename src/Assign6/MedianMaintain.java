package Assign6;

import javax.naming.directory.InvalidAttributesException;

public class MedianMaintain {
	
	MaxHeap max = null;
	
	MinHeap min = null;
	
	public MedianMaintain(int inputSize) throws InvalidAttributesException{
		
		if(inputSize <=0 )
			return;
		
		int heapSize = inputSize/2;
		
		max = new MaxHeap(heapSize);
		min = new MinHeap(heapSize);
		
	}
	
	public int getMedian() throws Exception{
		
		if(min.isEmpty() && max.isEmpty()){
			throw new Exception();
		}
		else if(min.isEmpty())
		{
			return max.peek();
		}
		else if(max.isEmpty())
		{
			return min.peek();
		}
		else
		{
			if(min.getIndex()==max.getIndex())
				return min.peek();
			else if(min.getIndex() > max.getIndex())
				return min.peek();
			else
				return max.peek(); 
		}
		
	}
	
	public void insert(int value){
		
		if(min.isEmpty() && max.isEmpty())
		{
			min.insert(value);
		}
		else if(max.isEmpty())
		{
			min.insert(value);
			max.insert(min.extract());
		}
		else if(min.isEmpty())
		{
			max.insert(value);
			min.insert(max.extract());
		}
		else
		{
			if(value > min.peek())
			{
				max.insert(value);
				
				if(max.getIndex()-min.getIndex() >=2)
					min.insert(max.extract());
			}
			else
			{
				min.insert(value);
				
				if(min.getIndex() - max.getIndex()>=2)
					max.insert(min.extract());
					
			}
		}
		
	}

}
