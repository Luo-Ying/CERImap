package application.model;

public abstract class OsmData {
	
	/*
	 * id of data(way)
	 */
	public long id;
	
	/*
	 * name of data(wat->tag)
	 */
	public String name;
	
	public OsmData(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
