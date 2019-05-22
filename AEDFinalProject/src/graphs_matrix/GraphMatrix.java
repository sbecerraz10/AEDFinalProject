package graphs_matrix;

import java.util.ArrayList;
import java.util.Arrays;
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
		
	public GraphMatrix(int numVertices) {
		super();
		adjacent = new double[numVertices][numVertices];
		next = new int[numVertices][numVertices];
		numVertex = 0;
		vertices = new HashMap<>();
		verticesLookup = new ArrayList<>();
		shortestPath = new ArrayList<>();
	}
	
	public void clearShortestPath() {
		shortestPath.clear();
	}
	
	public void addEdge(V v1, V v2, double distance) {
		addVertex(v1);
		addVertex(v2);
		
		int v1Index = vertices.get(v1);
		int v2Index = vertices.get(v2);
		if(distance != 0)adjacent[v1Index][v2Index] = distance;
	}
	
	public void addVertex(V v) {
		if(!vertices.containsKey(v)) {
			vertices.put(v, numVertex);
			verticesLookup.add(numVertex, v);
			numVertex++;
		}
	}

	

    public ArrayList<String> bfs(V start) {
    	ArrayList<String> bf = new ArrayList<String>();
        Queue<V> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices.size()]; 

        queue.add(start);
        int index = vertices.get(start);
        visited[index] = true;

        while(!queue.isEmpty()) {
            V v = queue.poll();
            bf.add(v + " ");
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

    public void dfs(V start) {
        boolean[] visited = new boolean[vertices.size()];
        dfs(start, visited);
    
    }

    private void dfs(V v, boolean[] visited) {
        System.out.print(v + " ");
        int index = vertices.get(v);
        visited[index] = true;

        List<V> adjacentVertices = getAdjacentVertices(v);
        for(V a : adjacentVertices) {
            int aIndex = vertices.get(a);
            if(!visited[aIndex]) {
                dfs(a, visited);
            }
        }
    }

    private List<V> getAdjacentVertices(V v) {
        int index = vertices.get(v);
        List<V> result = new ArrayList<>();
        for(int i=0; i<adjacent[index].length; i++) {
            if(adjacent[index][i] != 0 && adjacent[index][i] != Double.MAX_VALUE) {
                result.add(verticesLookup.get(i));
            }
        }
        return result;
    }
	
    
    
	
	public void floydWarshall(int[][] weights, int numVertices) {
		 
        double[][] dist = new double[numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);
 
        for (int[] w : weights)
            dist[w[0] - 1][w[1] - 1] = w[2];
 
        int[][] nextAux = new int[numVertices][numVertices];
        for (int i = 0; i < nextAux.length; i++) {
            for (int j = 0; j < nextAux.length; j++)
                if (i != j)
                    nextAux[i][j] = j + 1;
        }
 
        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        nextAux[i][j] = nextAux[i][k];
                    }
 
        for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				System.out.print(dist[i][j]);
			}
			System.out.println();
		}
        
        for (int i = 0; i < nextAux.length; i++) {
			for (int j = 0; j < nextAux.length; j++) {
				System.out.print(nextAux[i][j] + "  ");
			}
			System.out.println();
		}
        
        next = nextAux;
        printResult(dist, nextAux);
    }
	
	public void printhPath(int i, int j) {
		if(i!=j) {
			printhPath(next[i][j], i); 
		}
		shortestPath.add(i);
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
}
