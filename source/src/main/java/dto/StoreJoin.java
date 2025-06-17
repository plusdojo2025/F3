package dto;

import java.io.Serializable;

// EmployeeテーブルのDTO（JavaBeans形式）
public class StoreJoin implements Serializable {
	private int user_id;
	private int icon_id;
	private String icon_name;
	private int price;
	private int point;
	public StoreJoin(int user_id, int icon_id, String icon_name, int price, int point) {
		super();
		this.user_id = user_id;
		this.icon_id = icon_id;
		this.icon_name = icon_name;
		this.price = price;
		this.point = point;
	}
	public StoreJoin() {
		super();
		this.user_id = 0;
		this.icon_id = 0;
		this.icon_name = "";
		this.price = 0;
		this.point = 0;
	}
	public int getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
