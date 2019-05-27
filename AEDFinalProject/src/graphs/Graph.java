package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph<V> implements IGraph<V> {
	
	private List<Nodo<V>> nodes;
	
	private int vertices;
	
	private ArrayList<Edge<V>> allEdges;
	
	private int[][] next;
	
	private ArrayList<Integer> shortestPath;
	
    public Graph(int citys) {
		super();
		this.nodes = new ArrayList<Nodo<V>>();
		this.allEdges = new ArrayList<Edge<V>>();
		this.vertices = nodes.size();
		this.shortestPath = new ArrayList<Integer>();
		next = new int[citys][citys];
	}

	public void addNode(Nodo<V> node) {
        this.nodes.add(node);
        node.setIndex(nodes.size()-1);
        this.vertices++;
    }
 
    public List<Nodo<V>> getNodes() {
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
			Nodo<V> origin = nodes.get(i);
			for (int j = 0; j < origin.getAdjacents().size(); j++) {
				Edge<V> currentEdge = origin.getAdjacents().get(j);
				Nodo<V> destination = currentEdge.getDestination();
				matrix[origin.getIndex()][destination.getIndex()] = currentEdge.getDistance();
			}
		}
    	
    	return matrix;
    	
    }
    
    public double[][] floydWarshall(double graph[][]) 
    { 
    	double dist[][] = new double[graph.length][graph.length]; 
        int i, j, k; 
  
        
        for (i = 0; i < graph.length; i++) 
            for (j = 0; j < graph.length; j++) 
                dist[i][j] = graph[i][j]; 
  
        
        for (k = 0; k < graph.length; k++) 
        { 
            for (i = 0; i < graph.length; i++) 
            { 
                for (j = 0; j < graph.length; j++) 
                { 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
        
        printSolution(dist); 
        return dist;
        
    } 
  
   public void printSolution(double dist[][]) { 
        for (int i=0; i<dist.length; ++i) 
        { 
            for (int j=0; j<dist.length; ++j) 
            { 
                if (dist[i][j]==10000000) 
                    System.out.print(-1 + "  "); 
                else
                    System.out.print(dist[i][j]+"  "); 
            } 
            System.out.println(); 
        } 
    }
	
	
	public void printhPath(int i, int j) {
		if(i!=j) {
			printhPath(next[i][j], i); 
		}
		shortestPath.add(i);
	}
	
	

	public int getVertices() {
		return vertices;
	}

	public ArrayList<Edge<V>> prim(Nodo<V> r) {
		for(Nodo<V> c: this.getNodes()) {
			c.setDistance(1000000);
			c.setColor(Nodo.WHITE);
		}
		
		ArrayList<Edge<V>> edge = new ArrayList<>();
		
		r.setDistance(0);
		r.setPredecessor(null);
		
		Queue<Nodo<V>> pq = new PriorityQueue<Nodo<V>>();
		pq.offer(r);
		
		while(!pq.isEmpty()) {
			Nodo<V> u = pq.poll();
			
			for(int i = 0; i < nodes.size(); i++) {
				
				for(int j = 0; j < nodes.get(i).getAdjacents().size(); j++) {
					Edge<V> e = nodes.get(i).getAdjacents().get(i);
					Nodo<V> v = e.getDestination();
					
					if(v.getColor() == Nodo.WHITE && (e.getDistance() < v.getDistance())) {
						v.setDistance(e.getDistance());
						pq.offer(v);
						
						v.setPredecessor(u);
					}
					
					u.setColor(Nodo.BLACK);
				}
				
			}
		}
		return edge;
	}
	
	public ArrayList<Nodo<V>> bfs(Nodo<V> start) {
		ArrayList<Nodo<V>> bf = new ArrayList<Nodo<V>>();
		Queue<Nodo<V>> queue = new LinkedList<>();
		boolean[] visited = new boolean[vertices]; 

		queue.add(start);
		int index = nodes.indexOf(start);
		visited[index] = true;

		while(!queue.isEmpty()) {
			Nodo<V> v = queue.poll();
			bf.add(v);
	            //System.out.print(v + " ");
			List<Edge<V>> adjacentVertices = nodes.get(v.getIndex()).getAdjacents();
			for(Edge<V> a : adjacentVertices) {
				int adjInd = a.getDestination().getIndex();
				if(!visited[adjInd]) {
					queue.add(a.getDestination());
					visited[adjInd] = true;
				}
			}

		}
		return bf;

	}
	
	public ArrayList<Nodo<V>> dfs(Nodo<V> start) {
		ArrayList<Nodo<V>> d = new ArrayList<Nodo<V>>();
		boolean[] visited = new boolean[vertices];
		dfs(start, visited,d);
		return d;
	}

	private void dfs(Nodo<V> v, boolean[] visited, ArrayList<Nodo<V>> d) {
		d.add(v);

		int index = nodes.indexOf(v);
		visited[index] = true;

		List<Edge<V>> adjacentVertices = nodes.get(index).getAdjacents();

		for(Edge<V> a : adjacentVertices) {
			int aIndex = nodes.indexOf(a.getDestination());
			if(!visited[aIndex]) {
				Nodo<V> n = a.getDestination();
				dfs(n, visited,d);
			}
		}
	}

//	public Graph<V> kruskal() {
//		Graph<V> g = new Graph<>(vertices);
//		PriorityQueue<Edge<V>> pq = new PriorityQueue<>(allEdges.size());
//		
//		pq.addAll(ordenarEgde());
//		
//		Nodo<V> []parent = new Nodo[vertices];
//		makeSet(parent);
//		
//		
//		
//		
//		return g;
//	}
//	
//	private ArrayList<Edge<V>> ordenarEgde() {
//		ArrayList<Edge<V>> e = giveAllEdges();
//		for(int i = 0; i < e.size()-1; i++) {
//			for(int j =1; j < e.size(); j++) {
//				if(e.get(i).getDistance() >= e.get(j).getDistance()) {
//					Edge<V> aux = e.get(i);
//					e.set(i, e.get(j));
//					e.set(j, aux);
//					
//				}
//			}
//		}
//		return e;
//	}
//
//	private void makeSet(Nodo<V> n[]) {
//		for (int i = 0; i < nodes.size() ; i++) {
//          n[i] = nodes.get(i);
//		}
//	}
//	
	

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
//	
//	public ArrayList<Edge<V>> kruskal2() {
//		ArrayList<Edge<V>> edge = new ArrayList<>();
//		
//		PriorityQueue<Edge<V>> pq = new PriorityQueue<>(allEdges.size());
//		pq = sort();
//		
//		
//		
//		for(int i = 0; i < vertices; i++) {
//			
//		}
//		
//		return edge;
//	}
//	
//	private PriorityQueue<Edge<V>> sort() {
//		ArrayList<Edge<V>> e = allEdges;
//		PriorityQueue<Edge<V>> pq = new PriorityQueue<>();
//		for(int i = 0; i < e.size()-1; i++) {
//			for(int j =1; j < e.size(); j++) {
//				if(e.get(i).getDistance() >= e.get(j).getDistance()) {
//					Edge<V> aux = e.get(i);
//					e.set(i, e.get(j));
//					e.set(j, aux);
//					
//				}
//			}
//		}
//		pq.addAll(e);
//		return pq;
//	}
//	
}
