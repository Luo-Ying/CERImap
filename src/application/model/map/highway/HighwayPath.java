package application.model.map.highway;

import application.model.Node;
import application.model.OpenStreetMap;
import application.model.map.Area;
import application.model.map.Highway;

//import application.model.Color;

public abstract class HighwayPath extends Area implements Highway {
	
//	public double width;

	public HighwayPath(long id, String name, String color, String colorBord, double width) {
		super(id, name, color, colorBord, width);
		// TODO Auto-generated constructor stub
//		this.width = width;
	}
	
	public HighwayPath(long id, String color, String colorBord, double width) {
		super(id, color, colorBord, width);
		// TODO Auto-generated constructor stub
//		this.width = width;
	}
	
	public double getCoef() {
		long idStartNode = this.nodes.get(0);
		long idEndNode = this.nodes.get(this.nodes.size() - 1);
		
		Node nodeStart = OpenStreetMap.nodeList.get(idStartNode);
		Node nodeEnd = OpenStreetMap.nodeList.get(idEndNode);
		
		double diffX = nodeEnd.getLon() - nodeStart.getLon();
		double diffY = nodeEnd.getLat() - nodeStart.getLat();
		
		Double theta = Math.atan2(diffX, diffY);
		theta *= 180 / Math.PI;
		
		if (theta < 0) {
			theta = 360 + theta;
		}
		
		return theta;
	}
	
}
