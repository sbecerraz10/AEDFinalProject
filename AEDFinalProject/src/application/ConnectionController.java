package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import graphs.Edge;
import graphs.Nodo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.TrainStation;

public class ConnectionController implements Initializable {

	@FXML
	private ChoiceBox<String> from;

	@FXML
	private ChoiceBox<String> to;
	
	@FXML
	private TextField price;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	void accept(ActionEvent event) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Peligro");
		
		if(!from.getSelectionModel().isEmpty() && !to.getSelectionModel().isEmpty() && !price.getText().isEmpty()) {
			int indice = from.getSelectionModel().getSelectedIndex();
			int indice1 = to.getSelectionModel().getSelectedIndex();
			double auxPrice = 0.0;
			try {
				auxPrice = Double.parseDouble(price.getText());
			
				if(indice == indice1) {
					alert.setContentText("Los puntos de salida deben de ser diferentes a los de entrada. Elige ciudades diferentes.");
					alert.show();
				
				} else {
					TrainStation t1 = Main.getTrainNetwork().getListStation().get(indice);
					TrainStation t2 = Main.getTrainNetwork().getListStation().get(indice1);
					Main.getTrainNetwork().getMatrixNetwork().addEdge(t1, t2, auxPrice);
					Nodo<TrainStation> n = new Nodo<TrainStation>(t1);
					Nodo<TrainStation> n1 = new Nodo<TrainStation>(t2);
					int p = Main.getTrainNetwork().getNetwork().getNodes().size();
					Main.getTrainNetwork().getNetwork().addNode(n);
					Main.getTrainNetwork().getNetwork().addNode(n1);
					Edge<TrainStation> e = new Edge<TrainStation>(n, n1, auxPrice);
					Main.getTrainNetwork().getNetwork().getNodes().get(p).addEdge(e);
				
					toReturn();
				} 
				
			} catch (NumberFormatException e) {
				alert.setContentText("El precio debe de ser un valor numerico");
				alert.show();
			}
			
		} else {
			alert.setContentText("Por favor no deje campos vacios");
			alert.show();
		}
		
	}
	
	@FXML
	void cancel(ActionEvent event) {
		toReturn();
	}
	
	private void toReturn() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(("/application/Map.fxml")));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) btCancelar.getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<String> citys = new ArrayList<>();
		for(TrainStation t: Main.getTrainNetwork().getListStation()) {
			citys.add(t.getCityname());
		}
		from.setItems(FXCollections.observableArrayList(citys));
		to.setItems(FXCollections.observableArrayList(citys));
	}
	
	
}
