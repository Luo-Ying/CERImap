package application.model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

//import com.sun.tools.javac.code.Attribute.Array;

import application.model.Node;
import application.model.NodePath;
import application.model.OpenStreetMap;
import application.model.Way;
import application.model.map.Street;

/**
 * 
 * @author Yingqi LUO
 *
 */
public class ShortestPath {
	
	private NodePath startNode;
	private NodePath endNode;
	private NodePath nodeCurrent;
	
	private ArrayList<NodePath> neighbourNode;
	private ArrayList<NodePath> path;
	
	private ArrayList<NodePath> shortestPath;
	
	private HashMap<Long, Street> listStreet = new HashMap<Long, Street>();

	public ShortestPath(long idNodeFrom, long idNodeTo) {
		this.startNode = new NodePath(idNodeFrom);
		this.endNode = new NodePath(idNodeTo);
//		System.out.println("idNodeTo: "+idNodeTo);
		this.nodeCurrent = new NodePath(idNodeFrom);
		
		this.neighbourNode = new ArrayList<NodePath>();
		this.path = new ArrayList<NodePath>();
		this.shortestPath = new ArrayList<NodePath>();
		
		this.path.add(this.nodeCurrent);
		
		for(Way way: OpenStreetMap.wayList.values()) {
			for(int i=0; i<way.tagList.size(); i++) {
//				boolean highway = false;
				if(way.tagList.get(i).getKey().indexOf("highway") == 0) {
					
//					highway = true;
					for(int j=0; j<OpenStreetMap.highway_roadCar.length; j++) {
						if(way.tagList.get(i).getValue().indexOf(OpenStreetMap.highway_roadCar[j]) == 0) {
//							System.out.println(way.tagList.get(i).getValue());
							Street street = new Street(way.tagList.get(i).getValue(), way);
							this.listStreet.put(way.getId(), street);
						}
					}
					
					
				}
			}
		}
	}
	
	public ArrayList<NodePath> getShortesPath(){
//		ArrayList<NodePath> shortesPath = new ArrayList<>();
		while(this.nodeCurrent.getFatherNodePath() != null) {			
			this.shortestPath.add(this.nodeCurrent);
			this.nodeCurrent = this.nodeCurrent.getFatherNodePath();
			return getShortesPath();
		}
		return this.shortestPath;
	}
	
	public ArrayList<NodePath> algoGetShortestPath() {
		while(this.nodeCurrent.getId() != this.endNode.getId()) {
			this.addNeighbourNode();
			if(this.neighbourNode.size() > 0) {				
				this.getNodePathClosest();
				return algoGetShortestPath();
			}
			else {
				break;
			}
		}
		return this.path;
	}
	  
	public void addNeighbourNode() {
		for(Street street: this.listStreet.values()) {
			for(int i=0; i<street.getNodeList().size(); i++) {
				if(this.nodeCurrent.getId() == street.getNodeList().get(i)) {
					if(i > 0) {
						Node node = OpenStreetMap.nodeList.get(street.getNodeList().get(i-1));
						if(this.addIntoNeighbourNode(node)) {
							NodePath nodeCurrent = new NodePath(node, this.nodeCurrent, this.calculateDistance(node, this.endNode));
							this.neighbourNode.add(nodeCurrent);
						}
					}
					if(i < (street.getNodeList().size()-1)) {
						Node node = OpenStreetMap.nodeList.get(street.getNodeList().get(i+1));
						if(this.addIntoNeighbourNode(node)) {							
							NodePath nodeCurrent = new NodePath(node, this.nodeCurrent, this.calculateDistance(node, this.endNode));
							this.neighbourNode.add(nodeCurrent);
						}
					}
				}
			}
		}
	}
	
	public boolean addIntoNeighbourNode(Node node) {
		for(int i=0; i<this.neighbourNode.size(); i++) {
			if(node.getId() == this.neighbourNode.get(i).getId()) {
				return false;
			}
		}
		for(int i=0; i<this.path.size(); i++) {
			if(node.getId() == this.path.get(i).getId()) {
				return false;
			}
		}
		return true;
		
	}
	
	public void getNodePathClosest() {		// set this.nodeCurrent
		double minDistance = this.neighbourNode.get(0).getDistance();
		int item = 0;
//		int maxItem = 0;
		for(int i=1; i<this.neighbourNode.size(); i++) {
			if(this.neighbourNode.get(i).getDistance() < minDistance) {
				minDistance = this.neighbourNode.get(i).getDistance();
				item = i;
			}
		}
		for(int i=0; i<this.neighbourNode.size(); i++) {
			if(this.neighbourNode.get(i).getDistance()==minDistance && i>item) {
				item = i;
			}
		}
		this.nodeCurrent = this.neighbourNode.get(item);
		this.neighbourNode.remove(item);
		this.path.add(this.nodeCurrent);
	}
	
	public double calculateDistance(Node node1, NodePath node2) {
		return Math.pow(Math.sqrt(node2.getLat()-node1.getLat())+Math.sqrt(node2.getLon()-node1.getLon()), 1.0/2);
	}
	
	public double calculateDistance(NodePath node1, NodePath node2) {
		return Math.pow(Math.sqrt(node2.getLat()-node1.getLat())+Math.sqrt(node2.getLon()-node1.getLon()), 1.0/2);
	}
	
	public void addNeighbourNode(NodePath node) {
		this.neighbourNode.add(node);
	}
	
	public void addNodePath(NodePath nodePath) {
		this.path.add(nodePath);
	}
	
	public NodePath getStartNode() {
		return this.startNode;
	}
	
	public NodePath getEndNode() {
		return this.endNode;
	}
	
	public NodePath getNodeCurrent() {
		return this.nodeCurrent;
	}
	
	public ArrayList<NodePath> getListNeighbourNode() {
		return this.neighbourNode;
	}
	
	public ArrayList<NodePath> getListPath(){
		return this.path;
	}
	
}
