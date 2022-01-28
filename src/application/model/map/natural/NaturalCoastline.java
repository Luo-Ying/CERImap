package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Natural;

public class NaturalCoastline extends Area implements Natural {
	
	public NaturalCoastline(long id, String name) {
		super(id, name, Colors.Natural_water, Colors.Natural_water);
	}

}
