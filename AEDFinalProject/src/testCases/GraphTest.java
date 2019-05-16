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

}
