package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwaySecondary extends HighwayPath {

	public HighwaySecondary(long id, String name) {
		super(id, name, Colors.Highway_secondary, Colors.bord_Highway_secondary, 4);
		// TODO Auto-generated constructor stub
	}

	public HighwaySecondary(long id) {
		super(id, Colors.Highway_secondary, Colors.bord_Highway_secondary, 4);
		// TODO Auto-generated constructor stub
	}

}
