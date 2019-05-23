package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
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
		
		for(int k = 0; k < Main.getTrainNetwork().getListStation().size(); k++) {
		
			TrainStation t = Main.getTrainNetwork().getListStation().get(k);
			
			if(Main.getTrainNetwork().getMatrixNetwork().getVertices().containsKey(t)) {
			
				int pos = Main.getTrainNetwork().getMatrixNetwork().getVertices().get(t);
				
				for(int j = 0; j < tamanio; j++) {
				
					if(matrix[pos][j] != 0) {
						
						for(int i = 0; i < labels.size(); i++) {
							
							if(labels.get(i).getId().equalsIgnoreCase(t.getCityname())) {
								
								Line line = new Line(100,100,100,100);
								line.setStroke(Color.BLACK);
								
							}
						}
						
					}
				}
			}
		}
	}

//	loadLines();
//	Line line = new Line();
//	line.setStartX(pasto.getLayoutX());
//	line.setStartY(pasto.getLayoutY());
//	line.setEndX(bogota.getLayoutX());
//	line.setEndY(bogota.getLayoutY());
//	line.setStroke(Color.BLACK);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadLines();
		
//		ImageView img = new ImageView(new Image("/additional/flecha.png"));
//		img.fitHeightProperty();
//		img.fitWidthProperty();
//		img.setLayoutX(0);
//		img.setLayoutY(0);
//		img.setTranslateY(cali.getLayoutY());
//		pane.getChildren().add(img);
		
//		Group root = new Group();
//		//bending curve
//		CubicCurve curve1 = new CubicCurve();
//		System.out.println(cali.getLayoutX());
//		System.out.println(cali.getLayoutY());
//		System.out.println(bogota.getLayoutX());
//		System.out.println(bogota.getLayoutY());
//		curve1.setStartX(cali.getLayoutX());
//		curve1.setStartY(cali.getLayoutY());
//		curve1.setEndX(bogota.getLayoutX());
//		curve1.setEndY(bogota.getLayoutY());
//		curve1.setControlX1(curve1.getStartX()-100);
//		curve1.setControlX2(curve1.getEndX());
//		curve1.setControlY1(curve1.getStartY());
//		curve1.setControlY2(curve1.getEndY()+50);
//		curve1.setStroke(Color.BLACK);
//		curve1.setStrokeWidth(1);
//		curve1.setFill( null);
//		
//		double size=Math.max(curve1.getBoundsInLocal().getWidth(), curve1.getBoundsInLocal().getHeight());
//		double scale =size/4d;
//		
//		Point2D ori = eval(curve1,0);
//		Point2D tan = evalDt(curve1,0).normalize().multiply(scale);
//		
////		Path arrowIni = new Path();
//		//arrowIni.getElements().add(new MoveTo(ori.getX()+0.2*tan.getX()-0.2*tan.getY(), ori.getY()+0.2*tan.getY()+0.2*tan.getX()));
////		arrowIni.getElements().add(new LineTo(ori.getX(), ori.getY()));
////		arrowIni.getElements().add(new LineTo(ori.getX()+0.2*tan.getX()+0.2*tan.getY(),	ori.getY()+0.2*tan.getY()-0.2*tan.getX()));
//		
//		ori = eval(curve1,1);
//		tan = evalDt(curve1,1).normalize().multiply(scale);
//		
//		//Flecha final
//		Path arrowEnd=new Path();
//		arrowEnd.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),	ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
//		arrowEnd.getElements().add(new LineTo(ori.getX(), ori.getY()));
//		arrowEnd.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),	ori.getY()-0.2*tan.getY()-0.2*tan.getX()));
//		root.getChildren().addAll(curve1,arrowEnd);
//		pane.getChildren().add(root);
	}
		/**
		* Evaluate the cubic curve at a parameter 0<=t<=1, returns a Point2D
		* @param c the CubicCurve 
		* @param t param between 0 and 1
		* @return a Point2D 
		*/
		
//		private Point2D eval(CubicCurve c, float t){
//			Point2D p=new Point2D(Math.pow(1-t,3)*c.getStartX()+
//			3*t*Math.pow(1-t,2)*c.getControlX1()+
//			3*(1-t)*t*t*c.getControlX2()+
//			Math.pow(t, 3)*c.getEndX(),
//			Math.pow(1-t,3)*c.getStartY()+
//			3*t*Math.pow(1-t, 2)*c.getControlY1()+
//			3*(1-t)*t*t*c.getControlY2()+
//			Math.pow(t, 3)*c.getEndY());
//			return p;
//		}
		
		/**
		* Evaluate the tangent of the cubic curve at a parameter 0<=t<=1, returns a Point2D
		* @param c the CubicCurve 
		* @param t param between 0 and 1
		* @return a Point2D 
		*/
//		private Point2D evalDt(CubicCurve c, float t){
//		Point2D p=new Point2D(-3*Math.pow(1-t,2)*c.getStartX()+
//		3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlX1()+
//		3*((1-t)*2*t-t*t)*c.getControlX2()+
//		3*Math.pow(t, 2)*c.getEndX(),
//		-3*Math.pow(1-t,2)*c.getStartY()+
//		3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlY1()+
//		3*((1-t)*2*t-t*t)*c.getControlY2()+
//		3*Math.pow(t, 2)*c.getEndY());
//		return p;
//		}
//	
}
