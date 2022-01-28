package classes_javafx;

import application.controller.MapController;
import application.model.Draw;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/*
 * @Author: Yingqi LUO
 */

public class ResizableCanvas extends Canvas {
	
	private AnchorPane root;
	//TODO: faut reflechir comment resoudre le probleme de redessiner! (y 3 fois et meme choses mais pas meme taille, du coup casse le map)
	
//	MapController mapController = new MapController();
	 
	public ResizableCanvas(AnchorPane root) {
		this.root = root;
		// Redraw canvas when size changes.
		widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());
	}

	private void draw() {
//		double width = getWidth();
//		double height = getHeight();
//
//		GraphicsContext gc = getGraphicsContext2D();
//		gc.clearRect(0, 0, width, height);
//
//		gc.setStroke(Color.RED);
//		gc.strokeLine(0, 0, width, height);
//		gc.strokeLine(0, height, width, 0);
		
//		int r = 170;
//		int g = 211;
//		int b = 223;
		int r = 240;
		int g = 243;
		int b = 247;
		GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.rgb(r, g, b, .99));
        
		gc.fillRoundRect(getLayoutX()-300, getLayoutY(), getWidth(), getHeight(), 0, 0);
		
		if(getWidth()>0 && getHeight()>0) {
			
			Draw.initDonnee(getWidth(), getHeight());
			Draw.drawMap(gc, this.root);
		}
		
//		Draw.drawNodes(gc);
//		Draw.drawWay(gc);
//		Draw.drawWayIndiquer(gc);
		
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public double prefWidth(double height) {
//		System.out.println(getWidth());
		return getWidth();
	}

	@Override
	public double prefHeight(double width) {
//		System.out.println(getHeight());
		return getHeight();
	}
}