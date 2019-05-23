package graphs;

import java.util.ArrayList;
import java.util.List;

public interface IGraph<V> {

	public void addNode(Nodo<V> node);
	
	public List<Nodo<V>> getNodes();
	
	public ArrayList<Edge<V>> giveAllEdges();
	
	public double [][] listToMatrix();
	
	public void floydWarshall(int numVertices);
	
	public void printResult(double[][] dist, int[][] next);
	
	public int getVertices();
	
	public void prim(Nodo<V> r);
	
	public void bfs(Nodo<V> node) throws IllegalArgumentException;
	
}
