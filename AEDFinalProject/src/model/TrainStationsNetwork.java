package model;

import graphs.Graph;
import graphs_matrix.GraphMatrix;

public class TrainStationsNetwork {
	
	private Graph<TrainStation> network1;
	
	private GraphMatrix<TrainStation> matrixNetwork;

	public TrainStationsNetwork() {
		super();
		network1 = new Graph<TrainStation>();
		matrixNetwork = new GraphMatrix<TrainStation>(0);
		// TODO Auto-generated constructor stub
	}

	public Graph<TrainStation> getNetwork1() {
		return network1;
	}

	public void setNetwork1(Graph<TrainStation> network1) {
		this.network1 = network1;
	}

	public GraphMatrix<TrainStation> getMatrixNetwork() {
		return matrixNetwork;
	}

	public void setMatrixNetwork(GraphMatrix<TrainStation> matrixNetwork) {
		this.matrixNetwork = matrixNetwork;
	}
	
	
	
	
	

}
