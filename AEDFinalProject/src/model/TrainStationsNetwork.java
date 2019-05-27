package model;

import java.util.ArrayList;

import graphs.Graph;
import graphs_matrix.GraphMatrix;

public class TrainStationsNetwork {
	
	private Graph<TrainStation> network;
	
	private GraphMatrix<TrainStation> matrixNetwork;
	
	private GraphMatrix<TrainStation> matrixSubNetwork;

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
		listStation.add(new TrainStation("Medellin",0));
		listStation.add(new TrainStation("SantaMarta",1));
		listStation.add(new TrainStation("Cali",2));
		listStation.add(new TrainStation("Tolima",3));
		listStation.add(new TrainStation("Pasto",4));
		listStation.add(new TrainStation("Bogota",5));
		listStation.add(new TrainStation("Cucuta",6));
		listStation.add(new TrainStation("Bucaramanga",7));
		listStation.add(new TrainStation("Barranquilla",8));
		listStation.add(new TrainStation("Cartagena",9));
		listStation.add(new TrainStation("Huila",10));
		listStation.add(new TrainStation("Tunja",11));
		listStation.add(new TrainStation("Yopal",12));
		listStation.add(new TrainStation("Quibdo",13));
		listStation.add(new TrainStation("Arauca",14));
		listStation.add(new TrainStation("Manizales",15));
		listStation.add(new TrainStation("Guaviare",16));
		listStation.add(new TrainStation("Villavicencio",17));
	}
	
	public double[][] adjustMatrix() {
		double[][] toReturn;
		boolean [] arrVerify = new boolean[matrixNetwork.getAdjacent().length];
		ArrayList<TrainStation> stationsIn = new ArrayList<TrainStation>();
		
		
		for (int i = 0; i < matrixNetwork.getAdjacent().length; i++) {
			for (int j = 0; j < matrixNetwork.getAdjacent().length; j++) {
				if(matrixNetwork.getAdjacent()[i][j] != 0 && matrixNetwork.getAdjacent()[i][j] != Double.POSITIVE_INFINITY) {
					arrVerify[i] = true; arrVerify[j] = true;
				}	
			}				
		}
		int size = 0;
		for (int i = 0; i < arrVerify.length; i++) {
			if(arrVerify[i]) {size++;stationsIn.add(matrixNetwork.getVerticesLookup().get(i));}
		}
		
		
		
		toReturn = new double[size][size];
		
		int row = 0, column = 0;
		for (int i = 0; i < matrixNetwork.getAdjacent().length; i++) {
			if(arrVerify[i]) {
				for (int j = 0; j < matrixNetwork.getAdjacent().length; j++) {
					if(arrVerify[j]) {toReturn[row][column] = matrixNetwork.getAdjacent()[i][j]; column++;}
				}
				row++;
			}
		}
		
		matrixSubNetwork = new GraphMatrix<>(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrixSubNetwork.addEdge(stationsIn.get(i), stationsIn.get(j), toReturn[i][j]);
			}
		}
		
		
		return toReturn;
		
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
