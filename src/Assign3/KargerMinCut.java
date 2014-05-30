package Assign3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import util.Vertex;
import Assign2.QuickSort;

public class KargerMinCut {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("KragerMinCut");
		
		try 
		{
			String userDir = System.getProperty("user.dir");
			String filePath = userDir + "/resources/kargerMinCut.txt";
			Map<Integer,List<Integer>> adjList= util.FileReader.readAdjacencyList(filePath);
			System.out.println(adjList.size());
			
			
			
			
			int min = Integer.MAX_VALUE;
			for(int i =0; i<200; i++){
				KargerMinCut cutter = new KargerMinCut();
				int out = cutter.computeMinCut(adjList);
				Thread.sleep(50);
				System.out.println("Output = "+out);
				
				if(min > out)
					min = out;
				
				cutter = null;
			}
			
			System.out.println("final answer = "+min);

		
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static boolean isValid(Map<Integer,List<Integer>> input){
		
		Set<Integer> keySet = input.keySet();
		
		for(Integer key: keySet){
			
			List<Integer> row = input.get(key);
			if(row==null ||row.size()==0)
			{
				System.out.println(key +" = has a null or empty row");
				return false;
			}
					
			
            if(!keySet.containsAll(row)){
            	
            	System.out.println(row  +" = has elements that are not present in overall keySet");
            	return false;
            }
			
		}
		
		return true;
		
	}
	
	
	public int computeMinCut(Map<Integer,List<Integer>> inMap) throws Exception{
		
		
		if(!isValid(inMap))
			throw new Exception("map in invalid state");
		
		if(inMap.size() > 2)
		{
			Integer key  = getRandomKey(inMap.keySet());
			List<Integer> edges = inMap.get(key);
			
			Integer secKey  = getRandomKey(edges);
			
			inMap = contract(inMap,key,secKey);
			
			return computeMinCut(inMap);
			
		}
		else
		{
			Integer key = getRandomKey(inMap.keySet());
			return inMap.get(key).size();
		}
		

	}


	private Map<Integer, List<Integer>> contract(
			Map<Integer, List<Integer>> inMap, Integer key, Integer secKey) {
		

		System.out.println("key = "+key+", secKey = "+secKey);
		
		List<Integer> first = inMap.get(key);
		List<Integer> second = inMap.get(secKey);
		
		System.out.println("first = "+first+", second = "+second);
		
		first.addAll(second);
		inMap.remove(secKey);
	
		first = removeSelfLoopAndOldVal(first,key,secKey);
		
		inMap.put(key, first);
		
		
		inMap = updateMap(inMap,key,secKey);
		
		return inMap;
	}


	private List<Integer> removeSelfLoopAndOldVal(List<Integer> first, Integer key,
			Integer secKey) {
		
		System.out.println("In removeSelfLoopAndOldVal, first = "+first);
		System.out.println("In removeSelfLoopAndOldVal, key = "+key+", secKey = "+secKey);
		
		Set<Integer> numSet = new TreeSet<Integer>();
		numSet.add(key);
		numSet.add(secKey);
		
		
		int preFilterSize = first.size();
		
		first.removeAll(numSet);
		assert(!first.contains(key));
		assert(!first.contains(secKey));
		
		int postFilterSize = first.size();
		
		System.out.println("In, removeSelfLoopAndOldVal num elements removed = "+(preFilterSize - postFilterSize));
		
		return first;
		
	}


	private Integer getRandomKey(List<Integer> edges) {
		
		int size = edges.size();
		int item = new Random(System.currentTimeMillis()).nextInt(size);
		return edges.get(item);
	}


	private Integer getRandomKey(Set<Integer> keySet) {

		int size = keySet.size();
		int item = new Random(System.currentTimeMillis()).nextInt(size); // In real life, the Random object should be rather more shared than this
		int i = 0;
		Integer last = null;
		for(Integer obj : keySet)
		{
		    if (i == item)
		        return obj; 
		    
		    last = obj;
		    i++;
		}	
		
		return last;
		
	}


	private Map<Integer, List<Integer>>  updateMap(Map<Integer, List<Integer>> inMap, Integer newVal,
			Integer delVal) {
		
		System.out.println("in updateMap, replacing "+delVal+", with "+newVal);
		

		for(Integer key:inMap.keySet()){
			
			List<Integer> row = inMap.get(key);
			List<Integer> outRow = new LinkedList<Integer>();
			
			for(int i = 0; i < row.size(); i++)
			{
				if(row.get(i).equals(delVal))
				{
					
					outRow.add(i, newVal);
				}
				
				else
				{
					outRow.add(i,row.get(i));
				}
			}
			
			assert(!outRow.contains(delVal));
			inMap.put(key,outRow);
			
		
		}
		
		return inMap;
		
	}	
	

}
