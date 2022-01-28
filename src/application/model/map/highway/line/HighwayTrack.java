package application.model.map.highway.line;

import application.model.Colors;
import application.model.map.highway.HighwayLine;

public class HighwayTrack extends HighwayLine {

	public HighwayTrack(long id, String name) {
		super(id, name, Colors.Highway_track, 1.5, 2);
		// TODO Auto-generated constructor stub
	}
	
	public HighwayTrack(long id) {
		super(id, Colors.Highway_track, 1.5, 2);
		// TODO Auto-generated constructor stub
	}

}
