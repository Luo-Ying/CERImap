package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Natural;

public class NaturalGrassland extends Area implements Natural {
	
	public NaturalGrassland(long id, String name) {
		super(id, name, Colors.Natural_grassland, Colors.Natural_grassland);
	}

}
