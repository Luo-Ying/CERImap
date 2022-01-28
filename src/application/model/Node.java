package application.model;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;

public class Node {
	
	private long id;
	private double lat;
	private double lon;
	private int version;
//	TODO change the format of date
	private String timestamp;
//	TODO verify the whats the 'changeset'
	private String changeset;
	private long uid;
	private String user;
//	private String k;
//	private String v;
	
	public ArrayList<Tag> tagList = new ArrayList<Tag>();
//	public HashMap<String, String> tagList = new HashMap<String, String>();
	
	public Node() {
		
	}
	
	public Node(long id, double lat, double lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}
	
	public Node(long id, double lat, double lon, int version, String timestamp, String changeset, long uid, String user) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.version = version;
		this.timestamp = timestamp;
		this.changeset = changeset;
		this.uid = uid;
		this.user = user;
//		this.k = k;
//		this.v = v;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

//	TODO: ...
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getChangeset() {
		return changeset;
	}

	public void setChangeset(String changeset) {
		this.changeset = changeset;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void addTag(Tag tag) {
//		this.tagList.put(key, value);
		this.tagList.add(tag);
	}

//	public String getK() {
//		return k;
//	}
//
//	public void setK(String k) {
//		this.k = k;
//	}
//
//	public String getV() {
//		return v;
//	}
//
//	public void setV(String v) {
//		this.v = v;
//	}
	

}
