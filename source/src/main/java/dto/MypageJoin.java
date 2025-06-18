package dto;
import java.io.Serializable;
public class MypageJoin implements Serializable{
	private int user_id;
	private int point;
	private String degree_name;
	private String user_name;
	private int region_id;
	private String region_name;
	private String mail;
	private String icon_name;
	private int icon_id;
	
	public MypageJoin(int user_id,int point, String degree_name, String user_name, int region_id,String region_name, String mail,
			String icon_name, int icon_id) {
		super();
		this.user_id = user_id;
		this.point = point;
		this.degree_name = degree_name;
		this.user_name = user_name;
		this.region_id = region_id;
		this.region_name = region_name;
		this.mail = mail;
		this.icon_name = icon_name;
		this.icon_id = icon_id;
	}
	public MypageJoin() {
		super();
		this.user_id = 0;
		this.point = 0;
		this.degree_name = "";
		this.user_name = "";
		this.region_name = "";
		this.mail = "";
		this.icon_name = "";
		this.icon_id = 0;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(String degree_name) {
		this.degree_name = degree_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public int getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
