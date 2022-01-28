package application.model;

//import application.model.map.Street;

/**
 * 
 * @author Yingqi LUO
 *
 */
public class NodePath extends Node {
	
	private NodePath fatherNode = null;
	private double distance = 0.0;
	
	public NodePath(long idNode) {
		super(idNode, OpenStreetMap.nodeList.get(idNode).getLat(), OpenStreetMap.nodeList.get(idNode).getLon());
	}
	
	public NodePath(Node node, NodePath pereNode, double distance) {
		super(node.getId(), node.getLat(), node.getLon());
		this.fatherNode = pereNode;
		this.distance = distance;
	}
	
	public NodePath getFatherNodePath() {
		return this.fatherNode;
	}
	
	public double getDistance() {
		return this.distance;
	}

}
