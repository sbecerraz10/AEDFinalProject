package graphs;

import java.util.ArrayList;
import java.util.List;

public interface IGraph<V> {

	public void addNode(Nodo<V> node);
	
	public List<Nodo<V>> getNodes();
	
	public ArrayList<Edge<V>> giveAllEdges();
	
	public double [][] listToMatrix();
	
	public double[][] floydWarshall(double matrix [][]);
	
	public void printhPath(int i, int j);
	
	public int getVertices();
	
	public ArrayList<Edge<V>> prim(Nodo<V> r);
	
	public ArrayList<Nodo<V>> bfs(Nodo<V> node) throws IllegalArgumentException;
	
}
