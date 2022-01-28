package application.model.map.natural;

import application.model.Colors;
import application.model.map.Area;
import application.model.map.Highway;
import application.model.map.Natural;
import application.model.map.highway.HighwayPath;

public class NaturalTrees extends Area implements Natural,Highway {
	
	public NaturalTrees(long id) {
		super(id, Colors.Natural_tree, Colors.Area);
		// TODO Auto-generated constructor stub
	}

	public NaturalTrees(long id, String name, double width) {
		super(id, name, Colors.Natural_tree, Colors.Area, width);
		// TODO Auto-generated constructor stub
	}
	
	public NaturalTrees(long id, String name) {
		super(id, name, Colors.Natural_scrub, Colors.Natural_scrub);
	}

}
