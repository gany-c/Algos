package Assign6;

import javax.naming.directory.InvalidAttributesException;

public class MinHeap {
	
	private int[] holder = null;
	
	private int index = 0;
	
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
	
	public MinHeap(int capacity) throws InvalidAttributesException{
		
		if(capacity <= 0)
			throw new InvalidAttributesException();
		
		this.holder = new int[capacity];
		this.index = 0;
	}
	
	public boolean isEmpty(){
		return this.index <= 0;
	}
	
	public boolean isFull(){
		return this.index >= this.holder.length;
	}
	
	public int peek(){
		
		return this.holder[0];
	}
	
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
	
	private void correctDown(int i) {
		
		int left = 2*i+1;
		
		if(left >= index)
			return;
		else
		{
			int right =left+1;
			
			if(right >= index )
			{
				if(holder[i] > holder[left])
				{
					swap(i,left);
					//no need for a recursive call here because, we already know that the right sibling is out of bounds
					//so children will also definitely be.
				}

			}
			else
			{
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

	public void insert(int value) throws Exception{
		
		if(index >= holder.length)
			throw new Exception("out of storage space");
		
		holder[this.index] = value;
		bubbleUp(this.index);
		index++;
		
	}

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
		heap.insert(5);
		heap.insert(3);
		heap.insert(66);
		heap.insert(4);
		heap.insert(2);
		heap.insert(283);
		//heap.display();
		System.out.println(heap.extract());
		

	}



	public int getIndex() {
		return index;
	}



}
