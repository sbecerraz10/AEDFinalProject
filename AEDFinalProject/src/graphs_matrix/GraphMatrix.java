package graphs_matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GraphMatrix<V> implements IGraphMatrix<V> {
	
	private Map<V, Integer> vertices;
	
	private double[][] adjacent;
	
	private List<V> verticesLookup;
	
	private int numVertex;
	
	private int[][] next;
	
	private ArrayList<Integer> shortestPath;
	
	private ArrayList<V> Q;
		
	public GraphMatrix(int numVertices) {
		super();
		adjacent = new double[numVertices][numVertices];
		next = new int[numVertices][numVertices];
		numVertex = 0;
		vertices = new HashMap<>();
		verticesLookup = new ArrayList<>();
		shortestPath = new ArrayList<>();
		
		for(int i = 0; i < adjacent.length; i++) {
			for(int j = 0; j < adjacent.length; j++) {
				if(i==j) {
					adjacent[i][j] = 0;
				}else if(i!=j) {
					adjacent[i][j] = Double.POSITIVE_INFINITY;
					adjacent[j][i] = Double.POSITIVE_INFINITY;
				} 
				next[i][j] = -1;
			}
		}
		
	}
	
	public Map<V, Integer> getVertices() {
		return vertices;
	}

	public void setVertices(Map<V, Integer> vertices) {
		this.vertices = vertices;
	}

	public double[][] getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(double[][] adjacent) {
		this.adjacent = adjacent;
	}

	public List<V> getVerticesLookup() {
		return verticesLookup;
	}

	public void setVerticesLookup(List<V> verticesLookup) {
		this.verticesLookup = verticesLookup;
	}

	public int getNumVertex() {
		return numVertex;
	}

	public void setNumVertex(int numVertex) {
		this.numVertex = numVertex;
	}

	public void clearShortestPath() {
		shortestPath.clear();
	}
	
	public void addEdge(V v1, V v2, double distance) {
		addVertex(v1);
		addVertex(v2);
		
		System.out.println(vertices.get(v1) + " " + vertices.get(v2));
		int v1Index = vertices.get(v1);
		int v2Index = vertices.get(v2);
		if(distance != 0)
			adjacent[v1Index][v2Index] = distance;
	}
	
	public void addVertex(V v) {
		if(!vertices.containsKey(v)) {
			System.out.println("Vertice numero: " + numVertex);
			vertices.put(v, numVertex);
			verticesLookup.add(numVertex, v);
			numVertex++;
		}
	}

	

    public ArrayList<V> bfs(V start) {
    	ArrayList<V> bf = new ArrayList<V>();
        Queue<V> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices.size()]; 

        queue.add(start);
        int index = vertices.get(start);
        visited[index] = true;

        while(!queue.isEmpty()) {
            V v = queue.poll();
            bf.add(v);
            //System.out.print(v + " ");

            List<V> adjacentVertices = getAdjacentVertices(v);
            for(V a : adjacentVertices) {
                int adjInd = vertices.get(a);
                if(!visited[adjInd]) {
                    queue.add(a);
                    visited[adjInd] = true;
                }
            }

        }return bf;

    }

    public ArrayList<V> dfs(V start) {
    	ArrayList<V> d = new ArrayList<V>();
        boolean[] visited = new boolean[vertices.size()];
        dfs(start, visited,d);
        return d;
    }

    private void dfs(V v, boolean[] visited, ArrayList<V> d) {
        //System.out.print(v. + " ");
    	d.add(v);
        int index = vertices.get(v);
        visited[index] = true;

        List<V> adjacentVertices = getAdjacentVertices(v);
        for(V a : adjacentVertices) {
            int aIndex = vertices.get(a);
            if(!visited[aIndex]) {
                dfs(a, visited,d);
            }
        }
    }

    private List<V> getAdjacentVertices(V v) {
        int index = vertices.get(v);
        List<V> result = new ArrayList<>();
        for(int i=0; i<adjacent[index].length; i++) {
            if(adjacent[index][i] != 0 && adjacent[index][i] != Double.POSITIVE_INFINITY) {
                result.add(verticesLookup.get(i));
            }
        }
        return result;
    }
	
    
    
    public double[][] floydWarshall(double graph[][]) 
    { 
    	double dist[][] = new double[graph.length][graph.length]; 
        int i, j, k; 
  
        
        for (i = 0; i < graph.length; i++) { 
            for (j = 0; j < graph.length; j++) {
                dist[i][j] = graph[i][j]; 
        		next[i][j] = j;
            }	
        }
        
        for (i = 0; i < graph.length; i++) { 
        		next[i][i] = i;
        }
        
        for (k = 0; k < graph.length; k++) 
        { 
            for (i = 0; i < graph.length; i++) 
            { 
                for (j = 0; j < graph.length; j++) 
                { 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) { 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                    	next[i][j] = next[i][k];
                    }	
                } 
            } 
        } 
        
        printSolution(dist); 
        return dist;
        
    } 
  
   public void printSolution(double dist[][]) 
    { 
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

	public int[][] getNext() {
		return next;
	}

	public void setNext(int[][] next) {
		this.next = next;
	}
	
//	public void Prim (WGraph g, V a) {
//		for (V v : g.getVertices()) {
//			//"Initialize" vertices in g
//			v.visit = false; //Not visisted yet
//			v.parent = Vertex.NIL_VERTEX; //No parent vertex in tree
//			v.dist = Integer.MAX_VALUE; //"Infinite" distance
//		}
//		a.dist = 0; //Initial vertex
//		//Create a new Priority Queue with all the vertices
//		Q = new ArrayList<>(g.getVertices());
//		while (!Q.esVacio()) { //While not empty
//			//Get minimum
//			Vertex v = Q.elimina();
//			//Then, for each neighbor
//			Lista<Edge> lv = g.getAdjacencies(v); //Adjacencies of v in g
//			for (Edge e : lv) {
//				Vertex w = e.v_f; //Neighbor
//				//If w haven't been visited (is still in Q) and e.cost < w.dist
//				//then the edge (v,w) is candidate for being in the tree
//				if (!w.visit && e.cost < w.dist) {
//					//New parent vertex.
//					w.parent = v;
//					w.dist = e.cost;
//					//Reorder heap.
//					Q.reordena(w);
//				}
//			}
//			v.visit = true;
//		}
//	}
}