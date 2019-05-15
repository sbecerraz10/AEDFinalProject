package graphs_matrix;

import java.util.HashMap;
import java.util.Map;

public class Graph<V> {
	
	private Map<V, Integer> vertices;
	
	private int[][] adjacent;
	
	private int numVertex;
	
	public Graph(int numVertices) {
		super();
		adjacent = new int[numVertices][numVertices];
		numVertex = 0;
		vertices = new HashMap<>();
	}
	
	public void addEdge(V v1, V v2) {
		addVertex(v1);
		addVertex(v2);
		
		int v1Index = vertices.get(v1);
		int v2Index = vertices.get(v2);
		adjacent[v1Index][v2Index] = 1;
	}
	
	public void addVertex(V v) {
		if(!vertices.containsKey(v)) {
			vertices.put(v, numVertex);
			numVertex++;
		}
	}

	
//	Implementacion clase no generica
//	private final int MAX_VERTEX;
//	private final int MAX_EDGE;
//	
//	private int numEdge;
//	private Node<V>[][] matrizAdjacent;
//	
//	public Graph(int numVertex) {
//		MAX_VERTEX = numVertex;
//		MAX_EDGE = numVertex*numVertex;
//		
//		this.numEdge = 0;
//		
//		matrizAdjacent = new Node<V>[MAX_VERTEX][MAX_VERTEX];
//	}
//	
//	public int getMAX_VERTEX() {
//		return MAX_VERTEX;
//	}
//	
//	public int getMAX_EDGE() {
//		return MAX_EDGE;
//	}
//	
//	public void addEdge(int v1, int v2) throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {
//		
//		if(v1 >= MAX_VERTEX || v2 >= MAX_VERTEX) {
//			throw new ArrayIndexOutOfBoundsException("Vertices invalidos, fuera de rango");
//		} else if(numEdge == MAX_EDGE) {
//			throw new UnsupportedOperationException("No se puede añadir mas aristas");
//		} else {
//			matrizAdjacent[v1][v2] = 1;
//		}
//		
//	}
//	
	
	
}
