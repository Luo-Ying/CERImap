package application.model.map;

import application.model.Colors;

public class Building extends Area {
	
//	protected String color = Color.Building;

	public Building(long id, String name) {
		super(id, name, Colors.Building, Colors.bord_Building);
		// TODO Auto-generated constructor stub
	}
	
	public Building(long id) {
		super(id, "", Colors.Building_non_name, Colors.bord_Building_non_name);
	}

}
