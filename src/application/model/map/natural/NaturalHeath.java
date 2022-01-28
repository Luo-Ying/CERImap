package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Natural;

public class NaturalHeath extends Area implements Natural {
	
	public NaturalHeath(long id, String name) {
		super(id, name, Colors.Natural_heath, Colors.Natural_heath);
	}
}
