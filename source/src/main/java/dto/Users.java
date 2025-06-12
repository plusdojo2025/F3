package dto;

import java.io.Serializable;

// EmployeeテーブルのDTO（JavaBeans形式）
public class Users implements Serializable {
	private int user_id;
	private int region_id;
	private int icon_id;
	private int degree_id;
	private String user_name;
	private String password;
	private String mail;
	public Users(int user_id, int region_id, int icon_id, int degree_id, String user_name, String password, String mail) {
		super();
		this.user_id = user_id;
		this.region_id = region_id;
		this.icon_id = icon_id;
		this.degree_id = degree_id;
		this.user_name = user_name;
		this.password = password;
		this.mail = mail;
	}
	public Users() {
		super();
		this.user_id = 0;
		this.region_id = 0;
		this.icon_id = 0;
		this.degree_id = 0;
		this.user_name = "";
		this.password = "";
		this.mail = "";
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public int getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}
	public int getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(int degree_id) {
		this.degree_id = degree_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
