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

public class FloydController implements Initializable{

	@FXML
	private Button btFloyd;

	@FXML
	private Button btDfs;

	@FXML
	private TextArea info;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		info.clear();
//		double [][] graph = Main.getTrainNetwork().getMatrixNetwork().getAdjacent();
//		double [][] dist = Main.getTrainNetwork().getMatrixNetwork().floydWarshall(graph);
//		info.setText(Main.getTrainNetwork().floydToText(dist));
		info.clear();
		double [][] graph = Main.getTrainNetwork().getMatrixNetwork().getAdjacent();
		double [][] dist = Main.getTrainNetwork().getMatrixNetwork().floydWarshall(graph);
		int [][] next = Main.getTrainNetwork().getMatrixNetwork().getNext();
		info.setText(Main.getTrainNetwork().giveAllPaths(dist, next));
	}

}
