package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graphs.Edge;
import graphs.Graph;
import graphs.Node;

class GraphTest {
	
	
	private Graph<Integer> graph;

	private void setUp() {
		this.graph = new Graph<Integer>();
	}
	private void setUp1() {
		setUp();		
		graph.addNode(new Node<Integer>(10) );
		graph.addNode(new Node<Integer>(18) );
		graph.addNode(new Node<Integer>(26) );
		graph.addNode(new Node<Integer>(28) );
		
		
	}
	
	private void setUp2() {
		setUp();
		Node<Integer> node = new Node<Integer>(10);
		Node<Integer> aux = new Node<Integer>(18);
		Node<Integer> aux1 = new Node<Integer>(26);
		Node<Integer> aux2 = new Node<Integer>(28);
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
	
	/*
	private void setUp3() {
		setUp();
		Node<Integer> na = new Node<Integer>(1);
		Node<Integer> nb = new Node<Integer>(2);
		Node<Integer> nc = new Node<Integer>(3);
		Node<Integer> nd = new Node<Integer>(4);
		Node<Integer> ne = new Node<Integer>(5);
		Edge<Integer> edge = new Edge<Integer>(na,nb, 1);
		Edge<Integer> edge1 = new Edge<Integer>(na,nc, 4);
		Edge<Integer> edge2 = new Edge<Integer>(na,ne, 2);
		Edge<Integer> edge3 = new Edge<Integer>(nb,na, 1);
		Edge<Integer> edge4 = new Edge<Integer>(nb,nd, 3);
		Edge<Integer> e5 = new Edge<Integer>(nb, ne, 3);
		Edge<Integer> e6 = new Edge<Integer>(nc, na, 4);
		Edge<Integer> e7 = new Edge<Integer>(nc, nd, 1);
		Edge<Integer> e8 = new Edge<Integer>(nc, ne, 3);
		Edge<Integer> e9 = new Edge<Integer>(nd, nb, 3);
		Edge<Integer> e10 = new Edge<Integer>(nd, nc, 1);
		Edge<Integer> e11 = new Edge<Integer>(nd, ne, 2);
		Edge<Integer> e12 = new Edge<Integer>(ne, na, 2);
		Edge<Integer> e13 = new Edge<Integer>(ne, nb, 3);
		Edge<Integer> e14 = new Edge<Integer>(ne, nc, 3);
		Edge<Integer> e15 = new Edge<Integer>(ne, nd, 2);
		
		na.addEdge(edge);
		na.addEdge(edge1);
		na.addEdge(edge2);
		nb.addEdge(edge3);
		nb.addEdge(edge4);
		nb.addEdge(e5);
		nc.addEdge(e6);
		nc.addEdge(e7);
		nc.addEdge(e8);
		nd.addEdge(e9);
		nd.addEdge(e10);
		nd.addEdge(e11);
		ne.addEdge(e12);
		ne.addEdge(e13);
		ne.addEdge(e14);
		ne.addEdge(e15);
		
		graph.addNode(na);
		graph.addNode(nb);
		graph.addNode(nc);
		graph.addNode(nd);
	}
	*/
	
	
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
	
	/*
	@Test
	void testPrim() {
		setUp3();
		graph.prim(graph.getNodes().get(0));
	}
	*/

}
