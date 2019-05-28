package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.TrainStation;

public class PathsController implements Initializable{

	@FXML
	private Button btFloyd;

	@FXML
	private Button btDfs;

	@FXML
	private TextArea info;

	@FXML
	void dfs(ActionEvent event) {
		try {
			info.clear();
			String inf = ""; 
			ArrayList<TrainStation> df = Main.getTrainNetwork().getMatrixNetwork().dfs(Main.getTrainNetwork().getMatrixNetwork().getVerticesLookup().get(0));
			for(int i = 0; i < df.size(); i++) {
				if(i != df.size()-1) {
					inf += df.get(i).getCityname() + "-->";
				} else {
					inf += df.get(i).getCityname();
				}
				
			}
	    	info.setText(inf);
		} catch(IndexOutOfBoundsException i) {
			info.setText("No hay conexiones");
		}
	}

	@FXML
	void floyd(ActionEvent event) {
		info.clear();
		double [][] graph = Main.getTrainNetwork().getMatrixNetwork().getAdjacent();
		double [][] dist = Main.getTrainNetwork().getMatrixNetwork().floydWarshall(graph);
		info.setText(Main.getTrainNetwork().floydToText(dist));

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}

}
