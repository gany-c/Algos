package util;

import java.util.LinkedList;
import java.util.List;

public class Vertex {
	
	private int value;
	private List<Integer> edges = new LinkedList<Integer>();
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public List<Integer> getEdges() {
		return edges;
	}
	public void setEdges(List<Integer> edges) {
		this.edges = edges;
	}
	
	

}
