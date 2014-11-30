package Assign6;

import javax.naming.directory.InvalidAttributesException;

/**
 *  2 heaps 
 * @author Ramanan
 *
 */

public class MedianMaintain {
	
	private  int inputSize = 0;
	private  int max_size = 0;
	private  int min_size = 0;
	
	// holds smaller elements
	private MaxHeap max = null;
	
	// holds larger elements
	private MinHeap min = null;
	
	public void display(){
		System.out.println("max = ");
		this.max.display();
		System.out.println("min = ");
		this.min.display();
	}
	
	public MedianMaintain(int inputSize) throws InvalidAttributesException{
		
		if(inputSize <=0 )
			return;
		
		this.inputSize = inputSize;
		int heapSize = inputSize/2;
		
		this.max = new MaxHeap(heapSize);
		this.max_size = heapSize;
		
		this.min = new MinHeap(inputSize-heapSize);
		this.min_size = inputSize - heapSize;
		
	}
	
	public int getMedian() throws Exception{
		
		if(min.isEmpty() && max.isEmpty()){
			throw new Exception("the heaps are empty");
		}
		else if(min.isEmpty())
		{
			return max.peek();
		}

		else
		{
			System.out.println("indexes =  "+this.max.getIndex()+", "+this.min.getIndex());
			
			if(min.getIndex()==max.getIndex()||max.getIndex()>min.getIndex())
			{
				
				return max.peek();
			}
			
			else
				return min.peek(); 
		}
		
	}
	
	public void insert(int value) throws Exception{
		
		if(max.isFull()&&min.isFull())
		{
			throw new Exception("Out of memory");
		}
		if(min.isEmpty() && max.isEmpty())
		{
			max.insert(value);
		}
		else if(min.isEmpty())
		{
			max.insert(value);
			min.insert(max.extract());
			assert(max.peek() <= min.peek());
		}
		else
		{
			if(value < max.peek())
			{
				if(max.isFull())
				{	
					min.insert(max.extract());
					insert(value);
					
				}	
				else
				{
					max.insert(value);
					
					if(max.getIndex()-min.getIndex() >=2)
						min.insert(max.extract());
					
				}

			}
			else
			{
				if(min.isFull())
				{	
					max.insert(min.extract());
					insert(value);
					
				}
				else
				{
					min.insert(value);
					
					if(min.getIndex() - max.getIndex()>=2)
						max.insert(min.extract());
						
				}
				

			}
			assert(max.peek() <= min.peek());
		}
		
	}

}
