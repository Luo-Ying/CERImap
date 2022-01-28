package application.model.map.highway.line;

import application.model.Colors;
import application.model.map.highway.HighwayLine;

public class HighwaySteps extends HighwayLine {

	public HighwaySteps(long id) {
		super(id, Colors.Highway_steps, 2, 2.2);
		// TODO Auto-generated constructor stub
	}

	public HighwaySteps(long id, String name) {
		super(id, name, Colors.Highway_steps, 2, 2.2);
		// TODO Auto-generated constructor stub
	}


}
