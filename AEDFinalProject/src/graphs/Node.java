package graphs;

import java.util.ArrayList;
import java.util.List;

public class Node<V>{
	
	private V value;
    private List<Edge<V>> adjacents = new ArrayList<Edge<V>>();
 
    public Node(V city) {
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

}
