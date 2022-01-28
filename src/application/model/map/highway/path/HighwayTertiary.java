package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayTertiary extends HighwayPath {

	public HighwayTertiary(long id, String name) {
		super(id, name, Colors.Highway_tertiary, Colors.bord_Highway_tertiary, 4);
		// TODO Auto-generated constructor stub
	}

	public HighwayTertiary(long id) {
		super(id, Colors.Highway_tertiary, Colors.bord_Highway_tertiary, 4);
		// TODO Auto-generated constructor stub
	}

}
