package application.model.map;

import java.util.ArrayList;

import application.model.OpenStreetMap;
import application.model.OsmData;

/**
 * @Author: Yingqi
 */

public abstract class Area extends OsmData {
	
	public String color;
	public String colorBord;
	public double width;

	/**
	 * all the nodes of the area
	 */
	protected ArrayList<Long> nodes = new ArrayList<Long>();
	
	public Area(long id, String name) {
		super(id, name);
	}
	
	public Area(long id, String name, String color, String colorBord) {
		super(id, name);
		this.nodes = OpenStreetMap.wayList.get(id).nodeList;
		this.color = color;
		this.colorBord = colorBord;
	}
	
	public Area(long id, String color, String colorBord) {
		super(id, "");
		this.color = color;
		this.colorBord = colorBord;
	}
	
	public Area(long id, String name, String color, String colorBord, double width) {
		super(id, name);
		this.nodes = OpenStreetMap.wayList.get(id).nodeList;
		this.color = color;
		this.colorBord = colorBord;
		this.width = width;
	}
	
	public Area(long id, String color, String colorBord, double width) {
		super(id, "");
		this.nodes = OpenStreetMap.wayList.get(id).nodeList;
		this.color = color;
		this.colorBord = colorBord;
		this.width = width;
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
