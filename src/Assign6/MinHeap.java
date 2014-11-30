package Assign6;

import javax.naming.directory.InvalidAttributesException;

public class MinHeap {
	
	private int[] holder = null;
	
	private int index = 0;
	

	
	public MinHeap(int capacity) throws InvalidAttributesException{
		
		if(capacity <= 0)
			throw new InvalidAttributesException();
		
		this.holder = new int[capacity];
		this.index = 0;
	}
	
	public boolean isEmpty(){
		return this.index <= 0;
	}
	
	public int peek(){
		
		return holder[0];
	}
	
	public int extract(){
		
		if(index == 0)
			return Integer.MIN_VALUE;
		
		int out = holder[0];
		index--;
		
		if(index != 0)
		{
			holder[0] = holder[index];
			correctDown(0);
			
		}

		return out;
	}
	
	private void correctDown(int i) {
		
		int left = 2*i+1;
		
		if(left > index -1)
			return;
		else
		{
			int right = 2*i+2;
			
			if(right > index -1)
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
				if(holder[i] < holder[left] && holder[i] < holder[right])
					return;
				else if(holder[i] < holder[left] && holder[i] >= holder[right])
				{
					swap(i,right);
					correctDown(right);
				}
				else if(holder[i] >= holder[left] && holder[i] < holder[right])
				{
					swap(i,left);
					correctDown(left);
				}
				else
				{
					int big = left;
					
					if(holder[left] < holder[right])
						big = right;
					
					swap(i,big);
					correctDown(big);
				}
			}
		}
		
	}

	public void insert(int value){
		
		if(index >= holder.length)
			return;
		
		holder[this.index++] = value;
		bubbleUp(this.index-1);
		
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	public int getIndex() {
		return index;
	}



}
