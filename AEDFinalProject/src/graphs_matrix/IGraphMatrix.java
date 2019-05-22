package graphs_matrix;

import java.util.ArrayList;

public interface IGraphMatrix<V> {

	public void clearShortestPath();
	
	public void addEdge(V v1, V v2, double distance);
	
	public void addVertex(V v);
	
	public ArrayList<String> bfs(V start);
	
	public  void dfs(V start);
	
	public void floydWarshall(int[][] weights, int numVertices);
	
	public void printhPath(int i, int j);
	
	public void printResult(double[][] dist, int[][] next);
	
}
