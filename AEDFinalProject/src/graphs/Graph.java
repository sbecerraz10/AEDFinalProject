package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph<V> implements IGraph<V> {
	
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
    
    public void floydWarshall(int numVertices) {
    	double[][] weights = listToMatrix();
        double[][] dist = new double[numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);
 
        for (double[] w : weights)
            dist[(int) (w[0] - 1)][(int) (w[1] - 1)] = w[2];
 
        int[][] next = new int[numVertices][numVertices];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }
 
        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
 
        for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				System.out.print(dist[i][j]);
			}
			System.out.println();
		}
        
        for (int i = 0; i < next.length; i++) {
			for (int j = 0; j < next.length; j++) {
				System.out.print(next[i][j] + "  ");
			}
			System.out.println();
		}
        
        printResult(dist, next);
    }
    
    public void printResult(double[][] dist, int[][] next) {
        System.out.println("pair     dist    path");
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    String path = String.format("%d -> %d    %2d     %s", u, v,(int) dist[i][j], u);
                    do {
                        u = next[u - 1][v - 1];
                        path += " -> " + u;
                    } while (u != v);
                    System.out.println(path);
                }
            }
        }
    }

	public int getVertices() {
		return vertices;
	}

	public void prim(Node<V> r) {
		for(Node<V> c: this.getNodes()) {
			c.setDistance(1000000);
			c.setColor(Node.WHITE);
		}
		
		r.setDistance(0);
		r.setPredecessor(null);
		
		Queue<Node<V>> pq = new PriorityQueue<Node<V>>();
		pq.offer(r);
		
		while(!pq.isEmpty()) {
			Node<V> u = pq.poll();
			
			for(int i = 0; i < nodes.size(); i++) {
				
				for(int j = 0; j < nodes.get(i).getAdjacents().size(); j++) {
					Edge<V> e = nodes.get(i).getAdjacents().get(i);
					Node<V> v = e.getDestination();
					
					if(v.getColor() == Node.WHITE && (e.getDistance() < v.getDistance())) {
						v.setDistance(e.getDistance());
						pq.offer(v);
						v.setPredecessor(u);
					}
					
					u.setColor(Node.BLACK);
				}
				
			}
		}
	}
	
	public void bfs(Node<V> node) throws IllegalArgumentException {
		if(!nodes.contains(node)) {
			throw new IllegalArgumentException("Node not found");
		} else {
			for(Node<V> n: getNodes()) {
				if(!n.equals(node)) {
					n.setColor(Node.WHITE);
					n.setDistance(1000000000);
					n.setPredecessor(null);
				}
			}
			
			node.setColor(Node.GRAY);
			node.setDistance(0);
			node.setPredecessor(null);
			
			Queue<Node<V>> lq = new LinkedList<>();
			lq.offer(node);
			
			while(!lq.isEmpty()) {
				Node<V> au = lq.poll();
				
				for(int i = 0; i < nodes.size(); i++) {
					
					for(int j = 0; j < nodes.get(i).getAdjacents().size(); j++) {
						Edge<V> e = nodes.get(i).getAdjacents().get(i);
						Node<V> v = e.getDestination();
						
						if(v.getColor() == Node.WHITE) {
							v.setColor(Node.GRAY);
							v.setDistance(au.getDistance()+1);
							v.setPredecessor(au);
							lq.offer(v);
						}
					}
					
				}
				au.setColor(Node.BLACK);
			}
		}
	}
    
//    public void kruskalMST(){
//        PriorityQueue<Edge<V>> pq = new PriorityQueue<>(allEdges.size(), CompareTo.comparingInt(o -> o.weight));
//
//        //add all the edges to priority queue, //sort the edges on weights
//        for (int i = 0; i <allEdges.size() ; i++) {
//            pq.add(allEdges.get(i));
//        }
//
//        //create a parent []
//       // int [] parent = new int[vertices];
//        Node<V> [] parent = new Node<>[vertices];
//
//        //makeset
//        makeSet(parent);
//
//        ArrayList<Edge<V>> mst = new ArrayList<>();
//
//        //process vertices - 1 edges
//        int index = 0;
//        while(index<vertices-1){
//            Edge<V> edge = pq.remove();
//            //check if adding this edge creates a cycle
//            int x_set = find(parent, edge.getOrigin());
//            int y_set = find(parent, edge.getDestination());
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
    
	/*
	public ArrayList<Edge<V>> kruskal() {
		ArrayList<Edge<V>> aux = new ArrayList<>();
		int a = 0, i = 0;
		ArrayList<Node<V>> node = (ArrayList<Node<V>>) getNodes();
		ArrayList<Edge<V>> edge = giveAllEdges();		
		
	}
	*/
	
}
