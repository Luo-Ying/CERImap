module CERImap {
	requires javafx.controls;
	requires java.xml;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	
	exports classes_javafx;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.controller to javafx.fxml ;
//	opens org.openjfx.controllers to javafx.fxml ;
}
