package application.model;

public class Member {

	private long id;
	private String type;
	private String role;
	
	public Member() {
		
	}
	
	public Member(long id, String type, String role) {
		this.id = id;
		this.type = type;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
