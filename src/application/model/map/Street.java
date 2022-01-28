package application.model.map;

import java.util.ArrayList;

import application.model.Node;
import application.model.Way;

/**
 * 
 * @author Yingqi LUO
 *
 */
public class Street extends Line implements Highway{
	
	private long idStartNode;
	private long idEndNode;
	private long idMiddleNode;
	private ArrayList<Long> nodeList;
	
	public Street(Way way) {
		super(way.getId(), null);
		// TODO Auto-generated constructor stub
		this.idStartNode = way.nodeList.get(0);
		this.idEndNode = way.nodeList.get(way.nodeList.size()-1);
		this.idMiddleNode = way.nodeList.get((way.nodeList.size()-1)/2);
		this.nodeList = way.nodeList;
	}

	public Street(String name, Way way) {
		super(way.getId(), name, "");
		// TODO Auto-generated constructor stub
		this.idStartNode = way.nodeList.get(0);
		this.idEndNode = way.nodeList.get(way.nodeList.size()-1);
		this.idMiddleNode = way.nodeList.get((way.nodeList.size()-1)/2);
		this.nodeList = way.nodeList;
	}
	
	public long getIdStartNode() {
		return this.idStartNode;
	}
	
	public long getIdEndNode() {
		return this.idEndNode;
	}
	
	public long getIdMiddleNode() {
		return this.idMiddleNode;
	}
	
	public ArrayList<Long> getNodeList(){
		return this.nodeList;
	}

}
