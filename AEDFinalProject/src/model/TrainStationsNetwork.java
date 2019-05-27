package model;

import java.util.ArrayList;

import graphs.Graph;
import graphs_matrix.GraphMatrix;

public class TrainStationsNetwork {
	
	private Graph<TrainStation> network;
	
	private GraphMatrix<TrainStation> matrixNetwork;

	private ArrayList<TrainStation> listStation;
	
	public static final int NUM_CITYS = 18;
	
	public TrainStationsNetwork() {
		super();
		network = new Graph<TrainStation>(NUM_CITYS);
		matrixNetwork = new GraphMatrix<TrainStation>(NUM_CITYS);
		
		listStation = new ArrayList<>();
		loadStation();
	}
	
	private void loadStation() {
		listStation.add(new TrainStation("Medellin"));
		listStation.add(new TrainStation("SantaMarta"));
		listStation.add(new TrainStation("Cali"));
		listStation.add(new TrainStation("Tolima"));
		listStation.add(new TrainStation("Pasto"));
		listStation.add(new TrainStation("Bogota"));
		listStation.add(new TrainStation("Cucuta"));
		listStation.add(new TrainStation("Bucaramanga"));
		listStation.add(new TrainStation("Barranquilla"));
		listStation.add(new TrainStation("Cartagena"));
		listStation.add(new TrainStation("Huila"));
		listStation.add(new TrainStation("Tunja"));
		listStation.add(new TrainStation("Yopal"));
		listStation.add(new TrainStation("Quibdo"));
		listStation.add(new TrainStation("Arauca"));
		listStation.add(new TrainStation("Manizales"));
		listStation.add(new TrainStation("Guaviare"));
		listStation.add(new TrainStation("Villavicencio"));
	}

	public Graph<TrainStation> getNetwork() {
		return network;
	}

	public void setNetwork1(Graph<TrainStation> network) {
		this.network = network;
	}

	public GraphMatrix<TrainStation> getMatrixNetwork() {
		return matrixNetwork;
	}

	public void setMatrixNetwork(GraphMatrix<TrainStation> matrixNetwork) {
		this.matrixNetwork = matrixNetwork;
	}
	
	public ArrayList<TrainStation> getListStation() {
		return listStation;
	}
	
	public void setListStation(ArrayList<TrainStation> listStation) {
		this.listStation = listStation;
	}
	
}
