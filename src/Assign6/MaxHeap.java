package Assign6;

import javax.naming.directory.InvalidAttributesException;
/*
 * create a MaxHeap by just inverting the keys you pass into a minHeap
 */
public class MaxHeap extends MinHeap {

	public MaxHeap(int capacity) throws InvalidAttributesException {
		super(capacity);
		
	}
	
	public void insert(int value) throws Exception{
		super.insert(-1  * value);
	}
	
	public int extract() throws Exception{
		return -1 * super.extract();
	}
	
	public int peek(){
		return -1 * super.peek();
	}

}
