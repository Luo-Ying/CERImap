package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayPrimaryLink extends HighwayPath {

	public HighwayPrimaryLink(long id, String name) {
		super(id, name, Colors.Highway_primary_link, Colors.bord_Highway_primary_link, 4);
		// TODO Auto-generated constructor stub
	}

	public HighwayPrimaryLink(long id) {
		super(id, Colors.Highway_primary_link, Colors.bord_Highway_primary_link, 4);
		// TODO Auto-generated constructor stub
	}
	
}
