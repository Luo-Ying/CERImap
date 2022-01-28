package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayResidential extends HighwayPath {

	public HighwayResidential(long id) {
		super(id, Colors.Highway_residential, Colors.bord_Highway_residential, 5);
		// TODO Auto-generated constructor stub
	}

	public HighwayResidential(long id, String name) {
		super(id, name, Colors.Highway_residential, Colors.bord_Highway_residential, 5);
		// TODO Auto-generated constructor stub
	}

	
	
}
