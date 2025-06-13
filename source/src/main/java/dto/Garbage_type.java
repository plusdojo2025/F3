package dto;
import java.io.Serializable;
import java.sql.Date;

public class Garbage_type implements Serializable{
	private int region_id;
	private Date region_date;
	private String types;
	
	public Garbage_type(int region_id, Date region_date, String types) {
		super();
		this.region_id = region_id;
		this.region_date = region_date;
		this.types = types;
	}
	
	public Garbage_type() {
		super();
		this.region_id = 0;
		this.region_date = new Date(0);  // 1970-01-01 00:00:00 を初期値に設定
		this.types = "";
	}

	public int getRegion_id() {
		return region_id;
	}

	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}

	public Date getRegion_date() {
		return region_date;
	}

	public void setRegion_date(Date region_date) {
		this.region_date = region_date;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	
	
}


