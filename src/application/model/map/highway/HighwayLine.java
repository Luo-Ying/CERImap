package application.model.map.highway;

import application.model.map.Highway;
import application.model.map.Line;

public abstract class HighwayLine extends Line implements Highway {
	
	public double width;
	public double sizeDash;

	public HighwayLine(long id, String name, String color, double width, double sizeDash) {
		super(id, name, color);
		// TODO Auto-generated constructor stub
		this.width = width;
		this.sizeDash = sizeDash;
	}

	public HighwayLine(long id, String color, double width, double sizeDash) {
		super(id, color);
		// TODO Auto-generated constructor stub
		this.width = width;
		this.sizeDash = sizeDash;
	}
	

}
