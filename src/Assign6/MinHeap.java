package Assign6;

import javax.naming.directory.InvalidAttributesException;

/**
 * 
 * @author Ramanan
 * 
 * Min heap is implemented as an array based binary tree.
 * Node always has a lower value than both left and right child
 *
 */
public class MinHeap {
	
	//use an array
	private int[] holder = null;
	
	
	private int index = 0;
	
	//display contents of the array
	public void display(){
		if(holder == null||holder.length==0)
			return;
		else
		{
			System.out.println();
			for(int i=0;i<index;i++)
				System.out.print(holder[i]+", ");
			System.out.println();
		}
	}
	
	//constructor
	public MinHeap(int capacity) throws InvalidAttributesException{
		
		if(capacity <= 0)
			throw new InvalidAttributesException();
		
		this.holder = new int[capacity];
		this.index = 0;
	}
	
	//isEmpty
	public boolean isEmpty(){
		return this.index <= 0;
	}
	
	public boolean isFull(){
		return this.index >= this.holder.length;
	}
	
	public int peek(){
		
		return this.holder[0];
	}
	
	/*
	 * 1. return the topmost element
	 * 2. decrement the index
	 * 3. take bottom-most element and put it in the bottom.
	 * 4. correct the heap property downwards.
	 */
	public int extract() throws Exception{
		
		if(index <= 0)
			throw new Exception("heap is empty");
		

		
		int out = holder[0];
		index--;
		
		if(index > 0)
		{
			holder[0] = holder[index];
			correctDown(0);
			
		}

		
		return out;
	}
	
	/**
	 * 1. check if the root has children, otherwise return
	 * 2. if it has a left child alone, swap with the left child if root is smaller.
	 * 3. if it has both children, find the smaller of the two and swap with it. make a recursive call
	 * @param i
	 */
	private void correctDown(int i) {
		
		int left = 2*i+1;
		
		//check if the left child is within index, otherwise it has no children
		if(left >= index)
			return;
		else
		{
			//get index of right child 
			int right =left+1;
			
			//check if it falls within bounds
			if(right >= index )
			{
				//if left is smaller than root, swap.
				if(holder[i] > holder[left])
				{
					swap(i,left);
					//no need for a recursive call here because, we already know that the right sibling is out of bounds
					//so children will also definitely be.
				}

			}
			else
			{
				//if both children indexes are valid, find the smaller of the 2
				//swap the root with smaller,
				//make a recursive call.
				if(holder[i] <= holder[left] && holder[i] <= holder[right])
					return;

				else
				{
					int small = left;
					
					if(holder[left] > holder[right])
						small = right;
					
					swap(i,small);
					correctDown(small);
				}
			}
		}
		
	}

	/**
	 * 1. insert at the array's end;
	 * 2. correct the heap upwards.
	 * 3. update the array end pointer.
	 * @param value
	 * @throws Exception
	 */
	public void insert(int value) throws Exception{
		
		if(index >= holder.length)
			throw new Exception("out of storage space");
		
		holder[this.index] = value;
		bubbleUp(this.index);
		index++;
		
	}

	/**
	 * 
	 * @param i
	 * 
	 * 1. if you have reached the top, exit
	 * 2. Otherwise get the index of the parent, based on the current index being even or odd
	 * 3. Swap the parent and current if parent is larger than the current and continue recursively.
	 * 
	 */
	private void bubbleUp(int i) {
		
	  if(i == 0)
		  return;
		
	  int parent;
	  
	  if(i%2 == 0)
		  parent = i/2 -1;
	  else
		  parent = i/2;
	  
	  if(holder[parent] > holder[i])
	  {
		  swap(i,parent);
		  bubbleUp(parent);
	  }
		 
	}

	private void swap(int i, int parent) {
		
		int temp = holder[i];
		holder[i] = holder[parent];
		holder[parent] = temp;
		
	}

	public static void main(String[] args) throws Exception {
		
		MinHeap heap = new MinHeap(10);
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
	}



	public int getIndex() {
		return index;
	}



}
