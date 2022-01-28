package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayTrunk extends HighwayPath {

	public HighwayTrunk(long id, String name) {
		super(id, name, Colors.Highway_trunk, Colors.bord_Highway_trunk, 3.5);
		// TODO Auto-generated constructor stub
	}

	public HighwayTrunk(long id) {
		super(id, Colors.Highway_trunk, Colors.bord_Highway_trunk, 3.5);
		// TODO Auto-generated constructor stub
	}

}
