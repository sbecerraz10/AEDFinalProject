package graphs;

import java.util.ArrayList;
import java.util.List;

public interface IGraph<V> {

	public void addNode(Node<V> node);
	
	public List<Node<V>> getNodes();
	
	public ArrayList<Edge<V>> giveAllEdges();
	
	public double [][] listToMatrix();
	
	public void floydWarshall(int numVertices);
	
	public void printResult(double[][] dist, int[][] next);
	
	public int getVertices();
	
	public void prim(Node<V> r);
	
	public void bfs(Node<V> node) throws IllegalArgumentException;
	
}
