package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayTrunkLink extends HighwayPath {

	public HighwayTrunkLink(long id, String name) {
		super(id, name, Colors.Highway_trunk_link, Colors.bord_Highway_trunk_link, 3.5);
		// TODO Auto-generated constructor stub
	}
	
	public HighwayTrunkLink(long id) {
		super(id,  Colors.Highway_trunk_link, Colors.bord_Highway_trunk_link, 3.5);
		// TODO Auto-generated constructor stub
	}

}
