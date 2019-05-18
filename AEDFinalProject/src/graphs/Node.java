package graphs;

import java.util.ArrayList;
import java.util.List;

public class Node<V>{
	
	public final static int WHITE = 0;
	public final static int BLACK = 2;
	public final static int GRAY = 1;
	
	private int color;
	private V value;
    private List<Edge<V>> adjacents;
    private boolean visited;
	private Node<V> predecessor;
	private double distance = Double.MAX_VALUE;
	
	private int index;
 
    public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Node(V city) {
    	this.adjacents = new ArrayList<Edge<V>>();
        this.value = city;
    }
 
    public void addEdge(Edge<V> edge) {
        if(edge.getOrigin()==this)adjacents.add(edge);
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

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
