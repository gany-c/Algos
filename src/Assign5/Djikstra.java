package Assign5;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;

import util.Edge;


public class Djikstra {
	
	int[] findShortestPaths(int[][] adjMat){
		
		int[] output = new int[adjMat.length];
		
		Set<Integer> explored = new TreeSet<Integer>();
		explored.add(0);
		
		while(explored.size() < adjMat.length)
		{
			Edge next = findMinFrontierEdge(adjMat,explored);
			
			int start = next.getStart();
			int stop = next.getStop();
			explored.add(stop);
			
			output[stop] = output[start] +adjMat[start][stop];
			
			printStatus(explored);
		}
		
		
		return output;
	}

	private void printStatus(Set<Integer> explored) {
		
		System.out.println("---------------\n");
		for(Integer vertex:explored)
		{
			System.out.print(vertex+" ");
		}
		System.out.println("---------------");
		
	}

	private Edge findMinFrontierEdge(int[][] adjMat, Set<Integer> explored) 
	{
		Edge output = new Edge();
		int minEdge = Integer.MAX_VALUE;
		
		for(Integer vertex:explored)
		{
			int[] row = adjMat[vertex];
			
			for(int i =0;i<row.length;i++)
			{
				if(row[i]!=0 && (!explored.contains(row[i])) && row[i]<minEdge)
				{
					output.setStart(vertex);
					output.setStop(i);
					
					minEdge = row[i];
				}
			}
		}
		
		
		return output;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/resources/dijkstraData.txt";
		int[][] adjMat= util.FileReader.readAdjacencyMatrix(filePath,200);
		System.out.println(adjMat.length);
		
		for(int i = 0;i <adjMat.length;i++)
		{
			int[] inner = adjMat[i];
			for(int j = 0;j < inner.length; j++)
				System.out.print(inner[j]+" ");
			System.out.println();
		}
		
		Djikstra d = new Djikstra();
		int[] output = d.findShortestPaths(adjMat);
		
		System.out.println("------------");
		for(int i = 0; i< output.length;i++)
			System.out.print(output[i]);
		
		System.out.println("------------");
	}

}
