package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayMotorwayLink extends HighwayPath {

	public HighwayMotorwayLink(long id, String name) {
		super(id, name, Colors.Highway_motorway_link, Colors.bord_Highway_motorway_link, 4);
		// TODO Auto-generated constructor stub
	}
	
	public HighwayMotorwayLink(long id) {
		super(id, Colors.Highway_motorway_link, Colors.bord_Highway_motorway_link, 4);
		// TODO Auto-generated constructor stub
	}

}
