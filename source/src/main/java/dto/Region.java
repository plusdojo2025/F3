package dto;

import java.io.Serializable;

// EmployeeテーブルのDTO（JavaBeans形式）
public class Region implements Serializable {
	private int region_id;
	private String region_name;
	private String link;
	public Region(int region_id, String region_name, String link) {
		super();
		this.region_id = region_id;
		this.region_name = region_name;
		this.link = link;
	}
	public Region() {
		super();
		this.region_id = 0;
		this.region_name = "";
		this.link = "";
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
