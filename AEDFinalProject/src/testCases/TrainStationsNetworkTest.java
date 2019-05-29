package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TrainStationsNetwork;

class TrainStationsNetworkTest {

	private TrainStationsNetwork tsn;
	private double[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
	
	void setUp1() throws Exception {
		tsn = new TrainStationsNetwork();
	}
	

	@Test
	void LoadStationsTest() throws Exception {
		setUp1();
		tsn.loadStation();
		assertEquals(tsn.getListStation().get(0).getCityname(),"Medellin");
	}
	
	@Test
	void FloydToTextTest() throws Exception {
		setUp1();
		String inf = " ";
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				inf += mat[i][j] + "  ";
			}
			inf+= "\n";
		}
		assertEquals(tsn.floydToText(mat),inf);
	}

}
