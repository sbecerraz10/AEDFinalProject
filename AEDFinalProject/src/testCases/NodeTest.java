package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graphs.Edge;
import graphs.Nodo;

class NodeTest {
	
	
	private Nodo<Integer> node;
	private Nodo<Integer> aux;
	private Nodo<Integer> aux1;
	private Nodo<Integer> aux2;
	
	private void setUp() {
		this.node = new Nodo<Integer>(10);
	}
	
	private void setUp1() {
		aux = new Nodo<Integer>(18);
		aux1 = new Nodo<Integer>(26);
		aux2 = new Nodo<Integer>(28);
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
	}
	@Test
	void testAddEdge() {
		setUp();
		setUp1();
		
		
		assertEquals(1,node.getAdjacents().size());
		assertEquals(1,aux.getAdjacents().size());
		assertEquals(1,aux1.getAdjacents().size());
		assertEquals(2,aux2.getAdjacents().size());
		
		
		//Graph should look like this
		
//		10 -------> 18
//		^         / |^
//		|		/	||
//		|	  /
//		|	/		||
//		28          26
		
		
		
	}

	@Test
	void testGetAdjacents() {
		setUp();
		setUp1();
		assertEquals(aux.getValue(),node.getAdjacents().get(0).getDestination().getValue());
		assertEquals(aux1.getValue(),aux.getAdjacents().get(0).getDestination().getValue());
		assertEquals(aux.getValue(),aux1.getAdjacents().get(0).getDestination().getValue());
		assertEquals(node.getValue(),aux2.getAdjacents().get(0).getDestination().getValue());
		assertEquals(aux.getValue(),aux2.getAdjacents().get(1).getDestination().getValue());
		
		
	}

//	@Test
//	void testGetPredecessor() {
//		fail("Not yet implemented");
//	}

}
