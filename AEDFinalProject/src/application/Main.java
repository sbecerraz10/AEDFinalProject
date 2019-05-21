package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.TrainStationsNetwork;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static TrainStationsNetwork trainNetwork;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/Index.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Red-Colombiaria");
			primaryStage.getIcons().add(new Image("/additional/tren.png"));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		trainNetwork = new TrainStationsNetwork();
		launch(args);
	}

	public static TrainStationsNetwork getTrainNetwork() {
		return trainNetwork;
	}

	public static void setTrainNetwork(TrainStationsNetwork trainNetwork) {
		Main.trainNetwork = trainNetwork;
	}
}
