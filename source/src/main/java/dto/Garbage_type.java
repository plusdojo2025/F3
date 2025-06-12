package dto;
import java.io.Serializable;

public class Garbage_type implements Serializable{
	private int region_id;
	private String region_date;
	private String types;
	
	public Garbage_type(int region_id, String region_date, String types) {
		super();
		this.region_id = region_id;
		this.region_date = region_date;
		this.types = types;
	}
	
	public Garbage_type() {
		super();
		this.region_id = 0;
		this.region_date = "";
		this.types = "";
	}

	public int getRegion_id() {
		return region_id;
	}

	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}

	public String getRegion_date() {
		return region_date;
	}

	public void setRegion_date(String region_date) {
		this.region_date = region_date;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
}


