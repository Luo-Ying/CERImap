package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Natural;

public class NaturalWetland extends Area implements Natural {
	
	public NaturalWetland(long id, String name) {
		super(id, name, Colors.Natural_wetland, Colors.Natural_wetland);
	}

}
