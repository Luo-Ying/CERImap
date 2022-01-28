package application.model;

//import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//import application.model.Noeud;


/*
 * @Author: Yingqi LUO
 */

public class OpenStreetMap extends DefaultHandler {
	
	public static double minLat, maxLat, minLon, maxLon;
	
//	public static ArrayList<Node> nodeList;
	public static HashMap<Long, Node> nodeList = new HashMap<Long, Node>();
	
	public static HashMap<Long, Way> wayList = new HashMap<Long, Way>();
	
	public static HashMap<Long, Relation> relationList = new HashMap<>();
	
	public static String[] highway_roadCar = {"motorway", "trunk", "primary", "secondary", "tertiary", "unclassified", "residential", "pedestrian", "motorway_link", "trunk_link", "primary_link", "secondary_link", "tertiary_link", "track", "living_street" };
	
	public static String[] highway_roadFoot = {"living_street", "pedestrian", "footway", "bridleway", "steps", "corridor", "path"};
	
	Node node;
	Way way;
	Relation relation;
	Member member;
    //Ã¥â€¦Â¨Ã¥Â±â‚¬Ã¥ï¿½ËœÃ©â€¡ï¿½Ã§â€�Â¨Ã¦ï¿½Â¥Ã¨Â®Â°Ã¥Â½â€¢Ã¦Â¯ï¿½Ã¤Â¸â‚¬Ã¦Â¬Â¡Ã¦Å¸Â¥Ã¦â€°Â¾Ã¨Â§Â£Ã¦Å¾ï¿½Ã¥Ë†Â°Ã§Å¡â€žÃ¦Â â€¡Ã§Â­Â¾ Ã¦â€“Â¹Ã¤Â¾Â¿Ã¦Â¸â€¦Ã§Â©Âº
	String previousTagName;
	
	public OpenStreetMap() {
		
	}

//	public static void documentOpenStreetMap(String file) throws Exception {
//		
//		
//		
//	}
	
    @Override
/*  startElement(String uri,String localName,String qName,Attributes attributes)
 * qName - Ã©â„¢ï¿½Ã¥Â®Å¡Ã§Å¡â€žÃ¥ï¿½ï¿½Ã§Â§Â°Ã¯Â¼Ë†Ã¥Â¸Â¦Ã¦Å“â€°Ã¥â€°ï¿½Ã§Â¼â‚¬Ã¯Â¼â€°Ã¯Â¼Å’Ã¥Â¦â€šÃ¦Å¾Å“Ã©â„¢ï¿½Ã¥Â®Å¡Ã§Å¡â€žÃ¥ï¿½ï¿½Ã§Â§Â°Ã¤Â¸ï¿½Ã¥ï¿½Â¯Ã§â€�Â¨Ã¯Â¼Å’Ã¥Ë†â„¢Ã¤Â¸ÂºÃ§Â©ÂºÃ¥Â­â€”Ã§Â¬Â¦Ã¤Â¸Â²Ã£â‚¬â€š
 * attributes - Ã¥â€¦Æ’Ã§Â´Â Ã§Å¡â€žÃ¥Â±Å¾Ã¦â‚¬Â§Ã£â‚¬â€šÃ¥Â¦â€šÃ¦Å¾Å“Ã¦Â²Â¡Ã¦Å“â€°Ã¥Â±Å¾Ã¦â‚¬Â§Ã¯Â¼Å’Ã¥Ë†â„¢Ã¥Â®Æ’Ã¥Â°â€ Ã¦ËœÂ¯Ã§Â©ÂºÃ§Å¡â€ž Attributes Ã¥Â¯Â¹Ã¨Â±Â¡
 * */
 // Ã¦Â¯ï¿½Ã¨Â§Â£Ã¦Å¾ï¿½Ã¥Ë†Â° Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¥â€¦Æ’Ã§Â´Â Ã¯Â¼Ë†elementÃ¯Â¼â€°Ã§Å¡â€žÃ¦â€”Â¶Ã¥â‚¬â„¢Ã©Æ’Â½Ã¤Â¼Å¡Ã¨Â§Â¦Ã¥ï¿½â€˜Ã¨Â¿â„¢Ã¤Â¸ÂªÃ¥â€¡Â½Ã¦â€¢Â°Ã¯Â¼Å’Ã¥Â¹Â¶Ã¤Â¸â€�Ã¥Â°â€ Ã¨Â¿â„¢Ã¤Â¸ÂªelementÃ§Å¡â€žÃ¥Â±Å¾Ã¦â‚¬Â§attributesÃ¥â€™Å’Ã¥â‚¬Â¼valueÃ¥Â½â€œÃ¤Â½Å“Ã¥ï¿½â€šÃ¦â€¢Â°Ã¤Â¼Â Ã¨Â¿â€ºÃ¦ï¿½Â¥
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	
    	//Ã¦â€°Â¾Ã¥Ë†Â°Ã¥Â¼â‚¬Ã¥Â§â€¹Ã¦Â â€¡Ã§Â­Â¾
    	if(qName.equals("bounds")) {
    		String minLat = attributes.getValue("minlat");
    		String maxLat = attributes.getValue("maxlat");
    		String minLon = attributes.getValue("minlon");
    		String maxLon = attributes.getValue("maxlon");
    		OpenStreetMap.minLat = Double.valueOf(minLat);
    		OpenStreetMap.maxLat = Double.valueOf(maxLat);
    		OpenStreetMap.minLon = Double.valueOf(minLon);
    		OpenStreetMap.maxLon = Double.valueOf(maxLon);
    	}
    	
    	if(qName.equals("node")) {
    		this.node = new Node();
    		long id = Long.valueOf(attributes.getValue("id"));
    		double lat = Double.valueOf(attributes.getValue("lat"));
    		double lon = Double.valueOf(attributes.getValue("lon"));
    		int version = Integer.valueOf(attributes.getValue("version"));
    		String timestamp = attributes.getValue("timestamp");
    		String changeset = attributes.getValue("changeset");
    		long uid = Long.valueOf(attributes.getValue("uid"));
    		String user = attributes.getValue("user");
    		
//    		<node id="12116512" lat="43.2948921" lon="5.6357888" version="2" timestamp="2009-03-31T17:03:55Z" 
//    		changeset="873457" uid="112511" user="geraldgg"/>
    		
//    		public Node(long id, double lat, double lon, int version, String timestamp, String changeset, long uid, String user) {
    		
//    		node = new Node(id, lat, lon, version, timestamp, changeset, uid, user);
    		this.node.setId(id);
    		this.node.setLat(lat);
    		this.node.setLon(lon);
    		this.node.setVersion(version);
//    		TODO: definir le time et ajoute dedant
//    		this.node.setTimestamp(timestamp);
    		this.node.setChangeset(changeset);
    		this.node.setUid(uid);
    		this.node.setUser(user);
    		
    	}else if(qName.equals("tag")) {
//    		System.out.println("ok");
    		String k = attributes.getValue("k");
    		String v = attributes.getValue("v");
    		Tag tag = new Tag(k, v);
    		if(this.node != null) {
//    			System.out.println(tag.getValue());
    			this.node.addTag(tag);
    		}
    	}
    	
    	if(qName.equals("way")) {
    		this.way = new Way();
    		long id = Long.valueOf(attributes.getValue("id"));
    		int version = Integer.valueOf(attributes.getValue("version"));
    		String timestamp = attributes.getValue("timestamp");
    		String changeset = attributes.getValue("changeset");
    		long uid = Long.valueOf(attributes.getValue("uid"));
    		String user = attributes.getValue("user");
    		
    		this.way.setId(id);
    		this.way.setVersion(version);
//    		TODO: ...
    		this.way.setTimestamp(timestamp);
    		this.way.setChangeset(changeset);
    		this.way.setUid(uid);
    		this.way.setUser(user);
//    		private int version;
//    		private String timestamp;
//    		private String changeset;
//    		private long uid;
//    		private String user;
//    		
//    		public ArrayList<Long> nodeList = new ArrayList<Long>();
//    		
//    		public ArrayList<Tag> tagList = new ArrayList<Tag>();
    	}else if(qName.equals("nd")) {
    		if(way != null) {    			
    			long id = Long.valueOf(attributes.getValue("ref"));
    			way.addNode(id);    			
    		}
    	}else if(qName.equals("tag")) {
    		String k = attributes.getValue("k");
    		String v = attributes.getValue("v");
//    		Tag tag = new Tag(k, v);
    		if(way != null) {
    			way.addTag(k, v);
    		}
    	}
    	
    	if(qName.equals("relation")) {
    		this.relation = new Relation();
    		long id = Long.valueOf(attributes.getValue("id"));
    		int version = Integer.valueOf(attributes.getValue("version"));
    		String timestamp = attributes.getValue("timestamp");
    		String changeset = attributes.getValue("changeset");
    		long uid = Long.valueOf(attributes.getValue("uid"));
    		String user = attributes.getValue("user");
    		
    		this.relation.setId(id);
    		this.relation.setVersion(version);
    		this.relation.setTimestamp(timestamp);
    		this.relation.setChangeset(changeset);
    		this.relation.setUid(uid);
    		this.relation.setUser(user);
    		
    	}else if(qName.equals("member")) {
    		if(this.relation != null) {    		
    			this.member = new Member();
    			long id = Long.valueOf(attributes.getValue("ref"));
    			String type = attributes.getValue("type");
    			String role = attributes.getValue("role");
    			this.member.setId(id);
    			this.member.setType(type);
    			this.member.setRole(role);
    			this.relation.addMember(id, member);
    		}
    	}else if(qName.equals("tag")) {
    		String k = attributes.getValue("k");
    		String v = attributes.getValue("v");
//    		Tag tag = new Tag(k, v);
    		if(this.relation != null) {
    			this.relation.addTag(k, v);
    		}
    	}
    	
    	//Ã¦Å“Â¬Ã¦Â¬Â¡Ã¦Å¸Â¥Ã¦â€°Â¾Ã¥Â®Å’Ã¦Ë†ï¿½ Ã©Å“â‚¬Ã¨Â¦ï¿½Ã§Å¡â€žÃ¥Â±Å¾Ã¦â‚¬Â§Ã¥â‚¬Â¼Ã¥Â·Â²Ã§Â»ï¿½Ã¤Â¼Â Ã§Â»â„¢Ã¥Â¯Â¹Ã¨Â±Â¡
        previousTagName = qName;
    	
    }
    
 // Ã¥Â½â€œÃ¨Â§Â£Ã¦Å¾ï¿½Ã¥Ë†Â°Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¥â€¦Æ’Ã§Â´Â Ã¦Â â€¡Ã§Â­Â¾Ã§Å¡â€žÃ§Â»â€œÃ¦ï¿½Å¸Ã§Å¡â€žÃ¦â€”Â¶Ã¥â‚¬â„¢ Ã¤Â¼Å¡Ã¨Â°Æ’Ã§â€�Â¨
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	
    	//Ã¦â€°Â¾Ã¥Ë†Â°Ã¤Âºâ€ Ã§Â»â€œÃ¦ï¿½Å¸Ã¦Â â€¡Ã§Â­Â¾
        if (qName.equals("node")) {
        	OpenStreetMap.nodeList.put(this.node.getId(), this.node);
        	this.node = null;
        }//Ã¦Å“Â¬Ã¦Â â€¡Ã§Â­Â¾Ã¥â€ â€¦Ã§Å¡â€žÃ¦Å¸Â¥Ã¦â€°Â¾ Ã§Â»â€œÃ¦ï¿½Å¸ Ã¦Â¸â€¦Ã§Â©Âºtag
        
        if(qName.equals("way")) {
        	OpenStreetMap.wayList.put(this.way.getId(), this.way);
        	this.way = null;
        }
        
        if(qName.equals("relation")) {
        	OpenStreetMap.relationList.put(this.relation.getId(), this.relation);
        	this.relation = null;
        }
        
        previousTagName = "";
    }
    

}
