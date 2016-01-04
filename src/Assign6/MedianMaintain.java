package Assign6;

import javax.naming.directory.InvalidAttributesException;

/**
 *  2 heaps 
 * @author Ramanan
 *  PROBLEM:-  Create a data structure such that, 
 *  1. elements can be added at random
 *  2. should be be able to identify the median at any time
 *  
 *  
 *  
 *  SOLUTION:-
 *  
 *  1. Create 3 integer instance var for total size, max heap size and min heap size.
 *  2. Create a Max heap to hold the smaller elements,
 *  3. Create a Min heap to hold the larger elements.
 *  
 *  4. CONSTRUCTOR: Given a size, create max heap of half the size and min heap of remaining size
 *  
 *  INSERT METHOD:- 
 *  1. If both heaps are full, then set throw an exception
 *  2. If both heaps are empty then insert into the max heap
 *  
 *  3. If value is lesser than  Max's high
 *  3.1. If max heap is not full just add it there, do a check for size discrepancy of 2 between the 2 heaps,
 *  if yes do the step below
 *  3.1.1  else take the top of max heap and add it to min heap, and then add.
 * 
 * 4. Else if the Min heap is empty 
 * 4.1. do a transfer from min to max
 * 4.2 add to min heap
 * 4.3. again if the min heap is bigger than max by 2, do a min to max transfer
 * 
 * GET MEDIAN FUNCTION:
 * 
 * RETURN THE LARGER HEAP'S TOP OR RETURN ANY TOP.
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
