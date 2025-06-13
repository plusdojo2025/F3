package dto;
import java.io.Serializable;
import java.sql.Date;

public class HomeJoin implements Serializable{
	private String types;
	private String degree_name;
	private int score;
	private int point;
	private Date current;
	public HomeJoin(String types, String degree_name, int score, int point, Date current) {
		super();
		this.types = types;
		this.degree_name = degree_name;
		this.score = score;
		this.point = point;
		this.current = current;
	}
	public HomeJoin() {
		super();
		this.types = "";
		this.degree_name = "";
		this.score = 0;
		this.point = 0;
		this.current = new Date(0);  // 1970-01-01 00:00:00 を初期値に設定
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(String degree_name) {
		this.degree_name = degree_name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getCurrent() {
		return current;
	}
	public void setCurrent(Date current) {
		this.current = current;
	}
	
}
