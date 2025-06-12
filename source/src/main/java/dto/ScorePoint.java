package dto;

import java.io.Serializable;

// EmployeeテーブルのDTO（JavaBeans形式）
public class ScorePoint implements Serializable {
	private int user_id;
	private int score;
	private int point;
	public ScorePoint(int user_id, int score, int point) {
		super();
		this.user_id = user_id;
		this.score = score;
		this.point = point;
	}
	public ScorePoint() {
		super();
		this.user_id = 0;
		this.score = 0;
		this.point = 0;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
}
