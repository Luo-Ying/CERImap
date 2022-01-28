package application;
	
import java.io.File;
import java.net.URL;

import application.controller.MapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	
	public static Stage primaryStage;
	
	public static Scene initial;
	
	MapController mapController = new MapController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("CERImap");
//		Main.primaryStage.setResizable(true);
		
		initMapLayout(1000, 700);
		
		
	}
	
	
	/*
	 * Initializes the root layout 
	 */
	public void initMapLayout(int width, int height) throws Exception {
		try {
//			Load root layout from fxml file.
			Main.primaryStage.setWidth(width);
			Main.primaryStage.setHeight(height);
			
//			Label label = new Label("Loading...");
//	        label.setTextFill(Color.BLUE);
			
			System.out.println(System.getProperty("user.dir"));
			URL url = new File("src\\application\\vue\\MapLayout.fxml").toURI().toURL();
			Parent initialLayout = FXMLLoader.load(url);
			Main.initial = new Scene(initialLayout, width, height);
			
			primaryStage.setScene(initial);
			
			primaryStage.show();
			
//			mapController.drawMap(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
