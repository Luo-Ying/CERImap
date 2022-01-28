package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayTertiaryLink extends HighwayPath {

	public HighwayTertiaryLink(long id) {
		super(id, Colors.Highway_tertiary_link, Colors.bord_Highway_tertiary_link, 4);
		// TODO Auto-generated constructor stub
	}
	
	public HighwayTertiaryLink(long id, String name) {
		super(id, name, Colors.Highway_tertiary_link, Colors.bord_Highway_tertiary_link, 4);
		// TODO Auto-generated constructor stub
	}

}
