package application.model.map;

import java.util.ArrayList;

import application.model.OsmData;

public abstract class Line extends OsmData {
	
	public String color;
	
	/**
	 * all the nodes of the area
	 */
	protected ArrayList<Long> nodes = new ArrayList<Long>();
	
	public Line(long id) {
		super(id, "");
	}
	
	public Line(long id, String color) {
		super(id, "");
		this.color = color;
	}

	public Line(long id, String name, String color) {
		super(id, name);
		this.color = color;
	}
	

	public ArrayList<Long> getNodes() {
		return this.nodes;
	}

	public void addNodes(long id) {
		nodes.add(id);
	}
	
	public String toString() {
		return " [Name: " + this.name + ", Color: " + this.color + "] ";
	}

}
