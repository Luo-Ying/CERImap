package application.model.map.highway.line;

import application.model.Colors;
import application.model.map.highway.HighwayLine;

public class HighwayBusGuideway extends HighwayLine {

	public HighwayBusGuideway(long id) {
		super(id, Colors.Highway_bus_guideway, 1.5, 2.5);
		// TODO Auto-generated constructor stub
	}
	
	public HighwayBusGuideway(long id, String name) {
		super(id, name, Colors.Highway_bus_guideway, 1.5, 2.5);
		// TODO Auto-generated constructor stub
	}

}
