package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PathsController implements Initializable{

    @FXML
    private Button btFloyd;
 
    @FXML
    private Button btDfs;

    @FXML
    private TextArea info;

    @FXML
    void dfs(ActionEvent event) {
    	info.clear();
    	ArrayList df = Main.getTrainNetwork().getMatrixNetwork().dfs(Main.getTrainNetwork().getMatrixNetwork().getVerticesLookup().get(0));
    	info.setText(Main.getTrainNetwork().getMatrixNetwork().textDfs(df));
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
