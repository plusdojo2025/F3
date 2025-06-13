package dto;

import java.io.Serializable;

public class Degree implements Serializable{
	private int degree_id;
	private String degree_name;
	public Degree(int degree_id, String degree_name) {
		super();
		this.degree_id = degree_id;
		this.degree_name = degree_name;
	}
	public Degree() {
		this.degree_id = 0;
		this.degree_name = "";
	}
	public int getDegree_id() {
		return degree_id;
	}
	public void setDegree_id(int degree_id) {
		this.degree_id = degree_id;
	}
	public String getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(String degree_name) {
		this.degree_name = degree_name;
	}
}
