package graphs_matrix;

import java.util.ArrayList;

public interface IGraphMatrix<V> {

	public void clearShortestPath();
	
	public void addEdge(V v1, V v2, double distance);
	
	public void addVertex(V v);
	
	public ArrayList<V> bfs(V start);
	
	public  ArrayList<V> dfs(V start);
	
	public double[][] floydWarshall(double matrix [][]);
	
	public void printhPath(int i, int j);
	
	
}