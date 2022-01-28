package application.model;

import java.util.ArrayList;
import java.util.HashMap;


public class Relation {
	
	private long id;
	private int version;
	private String timestamp;
	private String changeset;
	private long uid;
	private String user;
	private String name;
	
//	public ArrayList<Long> member = new ArrayList<Long>();
	public HashMap<Long, Member> memberList = new HashMap<>();
	
	public ArrayList<Tag> tagList = new ArrayList<Tag>();
	
	public Relation() {
		
	}
	
	public Relation(long id, int version, String timestamp, String changeset, long uid, String user) {
		this.id = id;
		this.version = version;
		this.timestamp = timestamp;
		this.changeset = changeset;
		this.uid = uid;
		this.user = user;
	}

	public long getId() {
		return id;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addMember(long id, Member member) {
		this.memberList.put(id, member);
	}
	
	public void addTag(String k, String v) {
		this.tagList.add(new Tag(k, v));
	}

}
