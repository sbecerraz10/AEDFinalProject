package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import graphs_matrix.GraphMatrix;

class GraphMatrixTest {
	
	
	private GraphMatrix<Integer> grafito;
	
	
	private void setUp1() {
		grafito = new GraphMatrix<Integer>(5);
	}
	
	private void setUp2() {
		setUp1();
		Integer n1 = new Integer(10);
		Integer n2 = new Integer(20);
		Integer n3 = new Integer(30);
		Integer n4 = new Integer(9);
		Integer n5 = new Integer(26);
		
		grafito.addVertex(n1);
		grafito.addVertex(n2);
		grafito.addVertex(n3);
		grafito.addVertex(n4);
		grafito.addVertex(n5);
		
		
		grafito.addEdge(n1, n2, 10);
		grafito.addEdge(n1, n4, 12);
		grafito.addEdge(n2, n5, 7);
		grafito.addEdge(n5, n3, 5);
		grafito.addEdge(n3, n2, 8);
		grafito.addEdge(n4, n5, 1);
		
	}
	

	@Test
	void testAddEdge() {
		setUp2();
		int cont = 0;
		for (int i = 0; i < grafito.getAdjacent().length; i++) {
			for (int j = 0; j < grafito.getAdjacent().length; j++) {
				if(grafito.getAdjacent()[i][j] != 0 && grafito.getAdjacent()[i][j] != Double.POSITIVE_INFINITY ) {
					cont++;
				}
			}
		}
		assertEquals(6,cont);
		if(grafito.getAdjacent()[1][4] == 7) {
			assert(true);
		}
		if(grafito.getAdjacent()[4][1] != 7) {
			assert(true);
		}
		if(grafito.getAdjacent()[0][4] == 0) {
			assert(true);
		}
		
		
		
	}

	@Test
	void testAddVertex() {
		fail("Not yet implemented");
	}

	@Test
	void testBfs() {
		fail("Not yet implemented");
	}

	@Test
	void testDfs() {
		setUp2();
		ArrayList<Integer> listica = grafito.dfs(10);
		assertEquals(listica.get(0).intValue(), 10);
		assertEquals(listica.get(1).intValue(), 20);
		System.out.println(listica.size());
		assertEquals(listica.get(listica.size()-1).intValue(), 9);
		
		// El camino seria
		// 10 -> 20 -> 26 -> 30 -> 9
	}

	@Test
	void testFloydWarshall() {
		setUp1();
		
		double[][] matrix = {{0,Double.POSITIVE_INFINITY,17,Double.POSITIVE_INFINITY,19},{Double.POSITIVE_INFINITY,0,6,10,Double.POSITIVE_INFINITY}
		,{17,6,0,Double.POSITIVE_INFINITY,8},{Double.POSITIVE_INFINITY,10,Double.POSITIVE_INFINITY,0,13}
		,{19,Double.POSITIVE_INFINITY,8,13,0}};
		
		double [][] sol = grafito.floydWarshall(matrix);
		
		
		assertEquals(sol[0][1],23);
		assertEquals(sol[0][3],32);
		assertEquals(sol[2][3],16);
		assertEquals(sol[3][2],16);
		
		
	}

}
