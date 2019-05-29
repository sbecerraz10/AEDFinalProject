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


	public void loadStation() {
//		listStation.add(new TrainStation("Medellin",0));
//		listStation.add(new TrainStation("SantaMarta",1));
//		listStation.add(new TrainStation("Cali",2));
//		listStation.add(new TrainStation("Tolima",3));
//		listStation.add(new TrainStation("Pasto",4));
//		listStation.add(new TrainStation("Bogota",5));
//		listStation.add(new TrainStation("Cucuta",6));
//		listStation.add(new TrainStation("Bucaramanga",7));
//		listStation.add(new TrainStation("Barranquilla",8));
//		listStation.add(new TrainStation("Cartagena",9));
//		listStation.add(new TrainStation("Huila",10));
//		listStation.add(new TrainStation("Tunja",11));
//		listStation.add(new TrainStation("Yopal",12));
//		listStation.add(new TrainStation("Quibdo",13));
//		listStation.add(new TrainStation("Arauca",14));
//		listStation.add(new TrainStation("Manizales",15));
//		listStation.add(new TrainStation("Guaviare",16));
//		listStation.add(new TrainStation("Villavicencio",17));
		TrainStation t0 = new TrainStation("Medellin",0);
		TrainStation t1 =new TrainStation("SantaMarta",1);
		TrainStation t2 = new TrainStation("Cali",2);
		TrainStation t3 = new TrainStation("Tolima",3);
		TrainStation t4 = new TrainStation("Pasto",4);
		TrainStation t5 = new TrainStation("Bogota",5);
		TrainStation t6 = new TrainStation("Cucuta",6);
		TrainStation t7 = new TrainStation("Bucaramanga",7);
		TrainStation t72 = new TrainStation("Barranquilla",8);
		TrainStation t8 = new TrainStation("Cartagena",9);
		TrainStation t9 = new TrainStation("Huila",10);
		TrainStation t10 = new TrainStation("Tunja",11);
		TrainStation t11 = new TrainStation("Yopal",12);
		TrainStation t12 = new TrainStation("Quibdo",13);
		TrainStation t13 = new TrainStation("Arauca",14);
		TrainStation t14 = new TrainStation("Manizales",15);
		TrainStation t15 = new TrainStation("Guaviare",16);
		TrainStation t16 = new TrainStation("Villavicencio",17);
		listStation.add(t0);matrixNetwork.addVertex(t0);
		listStation.add(t1);matrixNetwork.addVertex(t1);
		listStation.add(t2);matrixNetwork.addVertex(t2);
		listStation.add(t3);matrixNetwork.addVertex(t3);
		listStation.add(t4);matrixNetwork.addVertex(t4);
		listStation.add(t5);matrixNetwork.addVertex(t5);
		listStation.add(t6);matrixNetwork.addVertex(t6);
		listStation.add(t7);matrixNetwork.addVertex(t7);
		listStation.add(t72);matrixNetwork.addVertex(t72);
		listStation.add(t8);matrixNetwork.addVertex(t8);
		listStation.add(t9);matrixNetwork.addVertex(t9);
		listStation.add(t10);matrixNetwork.addVertex(t10);
		listStation.add(t11);matrixNetwork.addVertex(t11);
		listStation.add(t12);matrixNetwork.addVertex(t12);
		listStation.add(t13);matrixNetwork.addVertex(t13);
		listStation.add(t14);matrixNetwork.addVertex(t14);
		listStation.add(t15);matrixNetwork.addVertex(t15);
		listStation.add(t16);matrixNetwork.addVertex(t16);
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
	
	
	public String floydToText(double[][] dist) {
		String str = " ";
		for (int i=0; i<dist.length; ++i) 
        { 
            for (int j=0; j<dist.length; ++j) 
            { 
                if (dist[i][j] == Double.MAX_VALUE) 
                    str += (-1 + "  "); 
                else
                    str += (dist[i][j]+"  "); 
            } 
            str += "\n"; 
        }
		return str; 
	}
	
	
	public String giveAllPaths(double[][] dist, int[][] next) {
		String str = "1.INITIAL CITY, 2.TARGET CITY,           3.PATH" + "\n";
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				if(dist[i][j] != Double.POSITIVE_INFINITY) {
					if(i!=j) {
						str += " 1."+listStation.get(i).getCityname() +"," + " 2."+listStation.get(j).getCityname() +",        " +" 3."+ pathUV(i, j, next);
						str += "\n";
					}
					
				}
			}
		}
		return str;
	}
	
	private String pathUV(int u, int v, int next[][]) {
		String str = "";
		if(next[u][v]!=-1) {
			str = "" + listStation.get(u).getCityname();
			while(u!=v) {
				u = next[u][v];
				str += "->>" + listStation.get(u).getCityname();
			}
		}return str;
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

	public GraphMatrix<TrainStation> getMatrixSubNetwork() {
		return matrixSubNetwork;
	}

	public void setMatrixSubNetwork(GraphMatrix<TrainStation> matrixSubNetwork) {
		this.matrixSubNetwork = matrixSubNetwork;
	}
	
	
}
