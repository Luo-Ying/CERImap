package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.HashMap;

public class Way {

	private long id;
	private int version;
//	TODO change the format of date
	private String timestamp;
//	TODO verify the whats the 'changeset'
	private String changeset;
	private long uid;
	private String user;
	
	public ArrayList<Long> nodeList = new ArrayList<Long>();
	
	public ArrayList<Tag> tagList = new ArrayList<Tag>();
//	public HashMap<String, String> tagList = new LinkedHashMap<String, String>();
	
	public Way() {
		
	}
	
	public Way(long id, int version, String timestamp, String changeset, long uid, String user) {
		this.id = id;
		this.version = version;
		this.timestamp = timestamp;
		this.changeset = changeset;
		this.uid = uid;
		this.user = user;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	
	public void addNode(long id) {
		this.nodeList.add(id);
	}
	
	public void addTag(String key, String value) {
//		this.tagList.put(key, value);
		Tag tag = new Tag(key, value);
		this.tagList.add(tag);
	}
}
