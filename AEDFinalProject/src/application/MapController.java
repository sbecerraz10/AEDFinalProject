package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.TrainStation;

public class MapController implements Initializable {
	
	@FXML
	private Label santaMarta;

	@FXML
	private Label medellin;

	@FXML
	private Label cali;

	@FXML
	private Label tolima;

	@FXML
	private Label pasto;

	@FXML
	private Label bogota;

	@FXML
	private Label cucuta;

	@FXML
	private Label bucaramanga;

	@FXML
	private Label barranquilla;

	@FXML
	private Label cartagena;

	@FXML
	private Label huila;

	@FXML
	private Label tunja;

	@FXML
	private Label yopal;
	
	@FXML
	private Label quibdo;

	@FXML
	private Label arauca;
	
	@FXML
	private Label manizales;

	@FXML
	private Label villavicencio;

	@FXML
	private Label guaviare;
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private ChoiceBox<TrainStation> from;
	
	@FXML
	private ChoiceBox<TrainStation> to;
	
	ArrayList<Label> labels = new ArrayList<>();
	
	@FXML
	void newConnection(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(("/application/Connection.fxml")));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadList() {
		labels.add(santaMarta);
		labels.add(medellin);
		labels.add(cali);
		labels.add(tolima);
		labels.add(pasto);
		labels.add(bogota);
		labels.add(cucuta);
		labels.add(bucaramanga);
		labels.add(barranquilla);
		labels.add(cartagena);
		labels.add(huila);
		labels.add(tunja);
		labels.add(yopal);
		labels.add(quibdo);
		labels.add(arauca);
		labels.add(manizales);
		labels.add(villavicencio);
		labels.add(guaviare);
	}
	
	private void loadLines() {
		loadList();
		
		int tamanio = Main.getTrainNetwork().getMatrixNetwork().getNumVertex();
		
		double[][] matrix = Main.getTrainNetwork().getMatrixNetwork().getAdjacent();
		
		for(int i = 0; i < Main.getTrainNetwork().getListStation().size(); i++) {
			TrainStation t = Main.getTrainNetwork().getListStation().get(i);
			
			if(Main.getTrainNetwork().getMatrixNetwork().getVertices().containsKey(t)) {
			
				int pos = Main.getTrainNetwork().getMatrixNetwork().getVertices().get(t);
				
				for(int j = 0; j < tamanio; j++) {
				
					if(matrix[pos][j] != 0 && matrix[pos][j] != Double.POSITIVE_INFINITY) {
						
						TrainStation aux = Main.getTrainNetwork().getMatrixNetwork().getVerticesLookup().get(j);
						Label l1 = searchLabel(t.getCityname());
						Label l2 = searchLabel(aux.getCityname());
						Arrow start = new Arrow(l1.getLayoutX()+i+1, l1.getLayoutY()+1+i, l2.getLayoutX()+1+i, l2.getLayoutY()+i+j);
						pane.getChildren().add(start);
					}
							
				}
			}
		}
	}
	
	private Label searchLabel(String name) {
		for(Label l: labels) {
			if(l.getId().equalsIgnoreCase(name)) {
				return l;
			}
		}
		return null;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadLines();
	}

	@FXML
	void price(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/ListPrice.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
