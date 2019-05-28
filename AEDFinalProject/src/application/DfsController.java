package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import model.TrainStation;

public class DfsController implements Initializable {

	@FXML
	private TextArea info;
	
	@FXML
	private ListView<String> list;
	
	@FXML
	void search(ActionEvent event) {
		try {
			info.clear();
			String inf = "";
			if(!list.getSelectionModel().isEmpty()) {
				
				int indice = list.getSelectionModel().getSelectedIndex();
				
				ArrayList<TrainStation> df = Main.getTrainNetwork().getMatrixNetwork().dfs(Main.getTrainNetwork().getListStation().get(indice));
				for(int i = 0; i < df.size(); i++) {
					if(i != df.size()-1) {
						inf += df.get(i).getCityname() + "-->";
					} else {
						inf += df.get(i).getCityname();
					}
					
				}
		    	info.setText(inf);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Peligro");
				alert.setContentText("Selecciones desde donde quiere hacer un recorrido");
				alert.show();
			}
		} catch(NullPointerException n) {
			info.setText("No hay conexiones");
		}
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<String> citys = new ArrayList<>();
		for(TrainStation t: Main.getTrainNetwork().getListStation()) {
			citys.add(t.getCityname());
		}
		list.setItems(FXCollections.observableArrayList(citys));

	}

}
