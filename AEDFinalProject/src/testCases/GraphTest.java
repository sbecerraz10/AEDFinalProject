package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import graphs.Edge;
import graphs.Graph;
import graphs.Nodo;

class GraphTest {
	
	
	private Graph<Integer> graph;

	private void setUp() {
		this.graph = new Graph<Integer>(18);
	}
	private void setUp1() {
		setUp();		
		graph.addNode(new Nodo<Integer>(10) );
		graph.addNode(new Nodo<Integer>(18) );
		graph.addNode(new Nodo<Integer>(26) );
		graph.addNode(new Nodo<Integer>(28) );
	}
	
	private void setUp2() {
		setUp();
		Nodo<Integer> node = new Nodo<Integer>(10);
		Nodo<Integer> aux = new Nodo<Integer>(18);
		Nodo<Integer> aux1 = new Nodo<Integer>(26);
		Nodo<Integer> aux2 = new Nodo<Integer>(28);
		Edge<Integer> edge = new Edge<Integer>(node,aux, 4);
		Edge<Integer> edge1 = new Edge<Integer>(aux,aux1, 7);
		Edge<Integer> edge2 = new Edge<Integer>(aux1,aux, 2);
		Edge<Integer> edge3 = new Edge<Integer>(aux2,node, 1);
		Edge<Integer> edge4 = new Edge<Integer>(aux2,aux, 6);
		
		node.addEdge(edge);
		aux.addEdge(edge1);
		aux1.addEdge(edge2);
		aux2.addEdge(edge3);
		aux2.addEdge(edge4);
		
		graph.addNode(node);
		graph.addNode(aux);
		graph.addNode(aux1);
		graph.addNode(aux2);
	}
	
	private void setUp3() {
		setUp();
	
		Nodo<Integer> x1 = new Nodo<Integer>(10);
		Nodo<Integer> x2 = new Nodo<Integer>(20);
		Nodo<Integer> x3 = new Nodo<Integer>(30);
		Nodo<Integer> x4 = new Nodo<Integer>(9);
		Nodo<Integer> x5 = new Nodo<Integer>(26);
		
		Edge<Integer> e1 = new Edge<>(x1, x2, 10);
		Edge<Integer> e2 = new Edge<>(x1, x4, 12);
		Edge<Integer> e3 = new Edge<>(x2, x5, 7);
		Edge<Integer> e4 = new Edge<>(x5, x3, 5);
		Edge<Integer> e5 = new Edge<>(x3, x2, 8);
		Edge<Integer> e6 = new Edge<>(x4, x5, 1);
		
		x1.addEdge(e1);
		x1.addEdge(e2);
		x2.addEdge(e3);
		x3.addEdge(e5);
		x4.addEdge(e6);
		x5.addEdge(e4);
		
		graph.addNode(x1);
		graph.addNode(x2);
		graph.addNode(x3);
		graph.addNode(x4);
		graph.addNode(x5);
		
	}
	
	@Test
	void testAddNode() {
		setUp();
		setUp1();
		if(graph.getVertices() == 4) {
			assert(true);
		}
		
		for (int i = 0; i < graph.getNodes().size(); i++) {
			if(graph.getNodes().get(i).getIndex() == i) {
				assert(true);
			}
		}
		
	}

	@Test
	void testListToMatrix() {
		setUp2();
		double[][] matrix = graph.listToMatrix();
		assertEquals(4,matrix[0][1]);
		assertEquals(7,matrix[1][2]);
		assertEquals(2,matrix[2][1]);
		assertEquals(1,matrix[3][0]);
		assertEquals(6,matrix[3][1]);
		//The way the matrix should look
//		   10 18 26 28
//		10| 0  4  0  0 
//		18| 0  0  7  0
//		26| 0  2  0  0
//		28| 1  6  0  0
	}
	
	
	@Test
	void testPrim() {
		setUp2();
		graph.prim(graph.getNodes().get(0));
		
	}
	
	@Test
	void testFloydWarshall() {
		setUp();
		
		double[][] matrix = {{0,Double.POSITIVE_INFINITY,17,Double.POSITIVE_INFINITY,19},{Double.POSITIVE_INFINITY,0,6,10,Double.POSITIVE_INFINITY}
		,{17,6,0,Double.POSITIVE_INFINITY,8},{Double.POSITIVE_INFINITY,10,Double.POSITIVE_INFINITY,0,13}
		,{19,Double.POSITIVE_INFINITY,8,13,0}};
		
		double [][] sol = graph.floydWarshall(matrix);
		
		
		assertEquals(sol[0][1],23);
		assertEquals(sol[0][3],32);
		assertEquals(sol[2][3],16);
		assertEquals(sol[3][2],16);		
	}
	
	@Test
	void testDfs() {
		setUp3();

		assertEquals(5, graph.getNodes().size());
		
		ArrayList<Nodo<Integer>> listica = graph.dfs(graph.getNodes().get(0));
		
		Nodo<Integer> n = new Nodo<Integer>(10);
		Nodo<Integer> n1 = new Nodo<Integer>(9);
		Nodo<Integer> n2 = new Nodo<Integer>(20);
		assertEquals(n.getValue(), listica.get(0).getValue());
		assertEquals(listica.get(1).getValue(), n2.getValue());
		assertEquals(listica.get(listica.size()-1).getValue(), n1.getValue());
		
		// El camino seria
		// 10 -> 20 -> 26 -> 30 -> 9
	}
	
	@Test
	void testBfs() {
		setUp3();
		ArrayList<Nodo<Integer>> listica = graph.bfs(graph.getNodes().get(0));
		
		Nodo<Integer> n = new Nodo<Integer>(10);
		Nodo<Integer> n3 = new Nodo<Integer>(26);
		Nodo<Integer> n1 = new Nodo<Integer>(9);
		Nodo<Integer> n4 = new Nodo<Integer>(30);
		Nodo<Integer> n2 = new Nodo<Integer>(20);
		assertEquals(listica.get(0).getValue(), n.getValue());
		assertEquals(listica.get(1).getValue(), n2.getValue());
		assertEquals(listica.get(2).getValue(), n1.getValue());
		assertEquals(listica.get(3).getValue(), n3.getValue());
		assertEquals(listica.get(4).getValue(), n4.getValue());
		
		// El camino seria
		// 10 -> 20 -> 9 -> 26 -> 30
		
	}
	
	

}
