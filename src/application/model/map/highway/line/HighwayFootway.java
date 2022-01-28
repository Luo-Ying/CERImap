package application.model.map.highway.line;

import application.model.Colors;
import application.model.map.highway.HighwayLine;

public class HighwayFootway extends HighwayLine {

	public HighwayFootway(long id) {
		super(id, Colors.Highway_footway, 1, 1.5);
		// TODO Auto-generated constructor stub
	}

	public HighwayFootway(long id, String name) {
		super(id, name, Colors.Highway_footway, 1, 1.5);
		// TODO Auto-generated constructor stub
	}
	
}
