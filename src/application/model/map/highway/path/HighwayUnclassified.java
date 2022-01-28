package application.model.map.highway.path;

import application.model.Colors;
import application.model.map.highway.HighwayPath;

public class HighwayUnclassified extends HighwayPath {

	public HighwayUnclassified(long id) {
		super(id, Colors.Highway_unclssified, Colors.bord_Highway_unclassified, 2.5);
		// TODO Auto-generated constructor stub
	}
	
	public HighwayUnclassified(long id, String name) {
		super(id, Colors.Highway_unclssified, Colors.bord_Highway_unclassified, 2.5);
		// TODO Auto-generated constructor stub
	}

}
