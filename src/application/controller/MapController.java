package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import application.Main;
import application.model.Draw;
import application.model.Itinerary;
import application.model.Node;
import application.model.OpenStreetMap;
import classes_javafx.ResizableCanvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;

/*
 * @Author: Yingqi LUO
 */

public class MapController implements Initializable{
	
	@FXML
	private AnchorPane root;
	
//	@FXML
//	private Canvas canvas;
	
	@FXML
	private StackPane stackPane;
	
	@FXML
	private ResizableCanvas resizableCanvas;
	
	@FXML
	private TextField city;
	
	@FXML
	private Button btnGO;
	
	@FXML
	private TextField numAddrFrom;
	
	@FXML
	private TextField addrFrom;
	
	@FXML
	private TextField numAddrTo;
	
	@FXML
	private TextField addrTo;
	
	@FXML
	private Button btnSearchItinerary;
	
	@FXML
	private Button btnStopSearching;
	
	GraphicsContext gc;
	
	
	
	/*
	 * draw the map
	 * 
	 * @Author: Yingqi
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
//		String file = "src\\application\\data\\avignon";
		String file = "src\\application\\data\\avignon1.osm";
//		String file = "src\\application\\data\\map_partie3Marseille";
//		String file = "src\\application\\data\\map_partie2Marseille";
//		String file = "src\\application\\data\\map_partieMarseille";
//		String file = "src\\application\\data\\map";
		
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
        	SAXParser sp = spf.newSAXParser();
        	XMLReader xmlReader = sp.getXMLReader();
        	xmlReader.setContentHandler(new OpenStreetMap());
        	xmlReader.parse(file);
//        	drawMap(Main.primaryStage);
        }catch(Exception e) {
        	System.out.println(e);
        }
		
        
	}
	
	
	
	public void drawMap(Stage primaryStage) {
		
//		resizableCanvas = new ResizableCanvas();
		ResizableCanvas canvas = new ResizableCanvas(this.root);
		
		
		root.getChildren().add(canvas);
	    root.setLeftAnchor(canvas, 300.0);
		 
		// Bind canvas size to stack pane size.
		canvas.widthProperty().bind(root.widthProperty());
		canvas.heightProperty().bind(root.heightProperty());
 

//		
		
	}
	
	public void getNameVille(ActionEvent event) {
		String nameVille = this.city.getText();
		System.out.println("nom de la ville" + nameVille);
		drawMap(Main.primaryStage);
	}
	
	
	public void searching(ActionEvent event) {
		
		String numFrom = this.numAddrFrom.getText();
		String numTo = this.numAddrTo.getText();
		
		String from = this.addrFrom.getText();
		String to = this.addrTo.getText();
		
		System.out.println("address from: " + numFrom + " " + from);
		System.out.println("address to: "+ numTo + " " + to);
		
		Itinerary itinerary = new Itinerary(numFrom, from, numTo, to);
		
		Draw.isSearched = true;
		
		Draw.idNodeFrom = itinerary.getIdHighway(numFrom, from);
		Draw.idNodeTo = itinerary.getIdHighway(numTo, to);
		
//		System.out.println(Draw.idNodeFrom);
//		System.out.println(Draw.idNodeTo);

		drawMap(Main.primaryStage);
		
	}
	
	public void stopSearching(ActionEvent event) {
		Draw.isSearched = false;
		drawMap(Main.primaryStage);
	}
	

}
