package dto;

import java.io.Serializable;
import java.sql.Date;

public class CalendarJoin implements Serializable{
	private Date current;
	private String types;
	private Date region_date;
	private String link;
	public CalendarJoin(Date current, String types, Date region_date, String link) {
		super();
		this.current = current;
		this.types = types;
		this.region_date = region_date;
		this.link = link;
	}
	public CalendarJoin() {
		super();
		this.current = new Date(0);  // 1970-01-01 00:00:00 を初期値に設定
		this.types = "";
		this.region_date = new Date(0);  // 1970-01-01 00:00:00 を初期値に設定
		this.link = "";
	}
	public Date getCurrent() {
		return current;
	}
	public void setCurrent(Date current) {
		this.current = current;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public Date getRegion_date() {
		return region_date;
	}
	public void setRegion_date(Date region_date) {
		this.region_date = region_date;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
