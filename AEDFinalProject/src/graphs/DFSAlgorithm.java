package graphs;

import java.util.HashSet;
import java.util.List;

public class DFSAlgorithm<V> {
	
	 private Graph<V> graph;
	 
	    public DFSAlgorithm(Graph<V> graph) {
	        this.graph = graph;
	    }
	 
	    private Nodo<V> getNode(V value) {
	        List<Nodo<V>> nodes = graph.getNodes();
	        for (Nodo<V> node : nodes) {
	            if (node.getValue().equals(value)) {
	                return node;
	            }
	        }
	        return null;
	    }
	 
	    public boolean hasPathDfs(V source, V destination) {
	        Nodo<V> start = getNode(source);
	        Nodo<V> end = getNode(destination);
	        if (start != null && end != null) {
	            return hasPathDfs(start, end, new HashSet<V>());
	        } else {
	            return false;
	        }
	    }
	 
	    private boolean hasPathDfs(Nodo<V> source, Nodo<V> destination, HashSet<V> visited) {
	        if (visited.contains(source.getValue())) {
	            return false;
	        }
	        visited.add(source.getValue());
	        if (source == destination) {
	            return true;
	        }
	        for (Edge<V> edge : source.getAdjacents()) {
	            if (hasPathDfs(edge.getDestination(), destination, visited)) {
	                return true;
	            }
	        }
	        return false;
	    }

}
