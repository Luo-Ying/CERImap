package application.model.map.highway.line;

import application.model.Colors;
import application.model.map.highway.HighwayLine;

public class HighwatLineObject extends HighwayLine {
	
	public HighwatLineObject(long id, String color, double sizeDash, double width) {
		super(id, color, sizeDash, width);
		// TODO Auto-generated constructor stub
	}
	
	public HighwatLineObject(long id, String name, String Color, double sizeDash, double width) {
		super(id, name, sizeDash, width);
		// TODO Auto-generated constructor stub
		this.color = color;
	}

}
