package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MapController {
	
	@FXML
	private ImageView santaMarta;

	@FXML
	private ImageView medellin;

	@FXML
	private ImageView cali;

	@FXML
	private ImageView tolima;

	@FXML
	private ImageView pasto;

	@FXML
	private ImageView bogota;

	@FXML
	private ImageView cucuta;

	@FXML
	private ImageView bucaramanga;

	@FXML
	private ImageView barranquilla;

	@FXML
	private ImageView cartagena;

	@FXML
	private ImageView huila;

	@FXML
	private ImageView tunja;

	@FXML
	private ImageView yopal;
	
	@FXML
	private ChoiceBox<String> from;

	@FXML
	private ChoiceBox<String> to;

	@FXML
	void newConnection(ActionEvent event) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/Connection.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	void accept(ActionEvent event) {

	}

	@FXML
	void cancel(ActionEvent event) {
		
	}
	
//	public void initialize() {
//		from.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//			@Override
//			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
////				System.out.println(tipoVehiculoChoiceBox.getItems().get((Integer) number2));
//				parqueadero.marcaHoraInicio(tipoVehiculoChoiceBox.getItems().get((Integer) number2));
//				parqueadero.marcarHoraFinal();
//				horaInicioTextField.setText(parqueadero.darHoraInicio());
//				horaFinalTextField.setText(parqueadero.darHoraFinal());
//			}
//		});
//		from.setItems(FXCollections.observableArrayList("Carro","Moto"));
//	}
//	
}
