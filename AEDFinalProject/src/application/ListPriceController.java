package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ListPriceController implements Initializable{

	@FXML
	private ListView<String> prices; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		prices.getItems().clear();
		prices.getItems().add("From\tTo\tPrice");
		String [] list = new String[Main.getTrainNetwork().getNetwork().giveAllEdges().size()];
		
		for(int i = 0; i < list.length; i++) {
			list[0] = Main.getTrainNetwork().getNetwork().giveAllEdges().get(i).getOrigin().getValue().getCityname() + "\t" + Main.getTrainNetwork().getNetwork().giveAllEdges().get(i).getDestination().getValue().getCityname() + "\t" + Main.getTrainNetwork().getNetwork().giveAllEdges().get(i).getDistance();
			prices.getItems().add(list[i]);
		}
		
	}

	
}
