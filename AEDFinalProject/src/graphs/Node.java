package graphs;

import java.util.ArrayList;
import java.util.List;

public class Node<V>{
	
	private V value;
    private List<Edge<V>> adjacents;
    private boolean visited;
	private Node<V> predecessor;
	private double distance = Double.MAX_VALUE;
 
    public Node(V city) {
    	this.adjacents = new ArrayList<Edge<V>>();
        this.value = city;
    }
 
    public void addEdge(Edge<V> edge) {
        adjacents.add(edge);
    }
 
    public List<Edge<V>> getAdjacents() {
        return adjacents;
    }
 
    public V getValue() {
        return value;
    }

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Node<V> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Node<V> predecessor) {
		this.predecessor = predecessor;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public void setAdjacents(List<Edge<V>> adjacents) {
		this.adjacents = adjacents;
	}

}
