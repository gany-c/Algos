package Assign6;

import javax.naming.directory.InvalidAttributesException;

public class MaxHeap extends MinHeap {

	public MaxHeap(int capacity) throws InvalidAttributesException {
		super(capacity);
		
	}
	
	public void insert(int value){
		super.insert(-1  * value);
	}
	
	public int extract(){
		return -1 * super.extract();
	}

}
