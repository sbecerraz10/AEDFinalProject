package graphs;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class DFSAlgorithm<V> {
	
	 private Graph<V> graph;
	 
	    public DFSAlgorithm(Graph<V> graph) {
	        this.graph = graph;
	    }
	 
	    private Node<V> getNode(V value) {
	        List<Node<V>> nodes = graph.getNodes();
	        for (Node<V> node : nodes) {
	            if (node.getValue().equals(value)) {
	                return node;
	            }
	        }
	        return null;
	    }
	 
	    public boolean hasPathDfs(V source, V destination) {
	        Node<V> start = getNode(source);
	        Node<V> end = getNode(destination);
	        if (start != null && end != null) {
	            return hasPathDfs(start, end, new HashSet<V>());
	        } else {
	            return false;
	        }
	    }
	 
	    private boolean hasPathDfs(Node<V> source, Node<V> destination, HashSet<V> visited) {
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
