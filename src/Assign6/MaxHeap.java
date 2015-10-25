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
	
	public static void main(String[] args) throws Exception {
		
		MaxHeap heap = new MaxHeap(10);
		heap.insert(50);
		heap.insert(3);
		heap.insert(6);
		heap.insert(41);
		heap.insert(2);
		heap.insert(-283);
		//heap.display();
		System.out.println(heap.extract());
		System.out.println(heap.extract());
		System.out.println(heap.extract());
		heap.insert(8);
		System.out.println(heap.extract());
		System.out.println(heap.extract());
		System.out.println(heap.extract());
		heap.insert(49);
		heap.insert(297);
		System.out.println(heap.extract());
	}


}
