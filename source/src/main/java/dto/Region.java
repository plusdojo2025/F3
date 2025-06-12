package dto;

import java.io.Serializable;

// EmployeeテーブルのDTO（JavaBeans形式）
public class Region implements Serializable {
	private int user_id;
	private String region_name;
	private String link;
	public Region(int user_id, String region_name, String link) {
		super();
		this.user_id = user_id;
		this.region_name = region_name;
		this.link = link;
	}
	public Region() {
		super();
		this.user_id = 0;
		this.region_name = "";
		this.link = "";
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
