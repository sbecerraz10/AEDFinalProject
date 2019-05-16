package graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph<V> {
	
	private List<Node<V>> nodes;
	
	private int vertices;
	
	private ArrayList<Edge<V>> allEdges;
	
    public Graph() {
		super();
		this.nodes = new ArrayList<Node<V>>();
		this.allEdges = new ArrayList<Edge<V>>();
		this.vertices = nodes.size();
	}

	public void addNode(Node<V> node) {
        this.nodes.add(node);
        node.setIndex(nodes.size()-1);
        this.vertices++;
    }
 
    public List<Node<V>> getNodes() {
        return nodes;
    }
    
    public ArrayList<Edge<V>> giveAllEdges(){
    	for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < nodes.get(i).getAdjacents().size(); j++) {
				if(!allEdges.contains(nodes.get(i).getAdjacents().get(j)))this.allEdges.add(nodes.get(i).getAdjacents().get(j));
			}
		}
    	
    	return this.allEdges;
    }
    
    public double [][] listToMatrix(){
		double [][] matrix = new double[this.vertices][this.vertices];
		for (int i = 0; i < nodes.size(); i++) {
			Node<V> origin = nodes.get(i);
			for (int j = 0; j < origin.getAdjacents().size(); j++) {
				Edge<V> currentEdge = origin.getAdjacents().get(j);
				Node<V> destination = currentEdge.getDestination();
				matrix[origin.getIndex()][destination.getIndex()] = currentEdge.getDistance();
			}
		}
    	
    	return matrix;
    	
    }

	public int getVertices() {
		return vertices;
	}

	
    
//    public void kruskalMST(){
//        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));
//
//        //add all the edges to priority queue, //sort the edges on weights
//        for (int i = 0; i <allEdges.size() ; i++) {
//            pq.add(allEdges.get(i));
//        }
//
//        //create a parent []
//       // int [] parent = new int[vertices];
//        Node<V> [] parent = new Node[vertices];
//
//        //makeset
//        makeSet(parent);
//
//        ArrayList<Edge> mst = new ArrayList<>();
//
//        //process vertices - 1 edges
//        int index = 0;
//        while(index<vertices-1){
//            Edge edge = pq.remove();
//            //check if adding this edge creates a cycle
//            int x_set = find(parent, edge.getOrigin());
//            int y_set = find(parent, edge.destination);
//
//            if(x_set==y_set){
//                //ignore, will create cycle
//            }else {
//                //add it to our final result
//                mst.add(edge);
//                index++;
//                union(parent,x_set,y_set);
//            }
//        }
//        //print MST
//        System.out.println("Minimum Spanning Tree: ");
//        printGraph(mst);
//    }
//
//    public void makeSet(Node<V> [] parent){
//        //Make set- creating a new element with a parent pointer to itself.
//        for (int i = 0; i < nodes.size() ; i++) {
//            parent[i] = nodes.get(i);
//        }
//    }
//
//    public int find(Node<V> [] parent, Node<V> vertex){
//        //chain of parent pointers from x upwards through the tree
//        // until an element is reached whose parent is itself
////        if(parent[vertex.getValue()]!=vertex)
////            return find(parent, parent[vertex]);;
////        return vertex;
//    	if() {
//    		
//    	}else {
//    		
//    	}
//    	
//    }
//
//    public void union(int [] parent, int x, int y){
//        int x_set_parent = find(parent, x);
//        int y_set_parent = find(parent, y);
//        //make x as parent of y
//        parent[y_set_parent] = x_set_parent;
//}
    
}
