package graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph<V> {
	
	private List<Node<V>> nodes = new ArrayList<Node<V>>();
	 
    public void addNode(Node<V> node) {
        nodes.add(node);
    }
 
    public List<Node<V>> getNodes() {
        return nodes;
    }

}
