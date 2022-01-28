package classes_javafx;

import application.model.Draw;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ResizableStackPane extends StackPane {
	
	public ResizableStackPane() {
		// TODO Auto-generated constructor stub
		widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());
	}

	@Override
	public boolean isResizable() {
		return true;
	}
	
	public void draw(){
//		System.out.println(this.getHeight());
	}


//	@Override
//	public double prefWidth(double height) {
//		return getWidth();
//	}
//
//	@Override
//	public double prefHeight(double width) {
//		return getHeight();
//	}
	
}
