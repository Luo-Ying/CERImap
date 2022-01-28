package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwaySecondaryLink extends HighwayPath {

	public HighwaySecondaryLink(long id) {
		super(id, Colors.Highway_secondary_link, Colors.bord_Highway_secondary_link, 4);
		// TODO Auto-generated constructor stub
	}
	
	public HighwaySecondaryLink(long id, String name) {
		super(id, name, Colors.Highway_secondary_link, Colors.bord_Highway_secondary_link, 4);
		// TODO Auto-generated constructor stub
	}

}
