package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Natural;

public class NaturalRocks extends Area implements Natural {

	public NaturalRocks(long id, String name) {
		super(id, name, Colors.Natural_bare_rock, Colors.Natural_bare_rock);
	}
	
}
