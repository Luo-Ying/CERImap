package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Natural;

public class NaturalBeach extends Area implements Natural {
	
	public NaturalBeach(long id, String name) {
		super(id, name, Colors.Natural_beach, Colors.Natural_beach);
	}

}
