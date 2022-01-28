package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Natural;

public class NaturalWater extends Area implements Natural {
	
	public NaturalWater(long id) {
		super(id, Colors.Natural_water, Colors.Natural_water);
		// TODO Auto-generated constructor stub
	}
	
	public NaturalWater(long id, String name) {
		super(id, name, Colors.Natural_water, Colors.Natural_water);
	}

}
