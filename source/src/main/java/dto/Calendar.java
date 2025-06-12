package dto;

import java.io.Serializable;

public class Calendar implements Serializable{
private int user_id;
private String date;
public Calendar(int user_id, String date) {
	super();
	this.user_id = user_id;
	this.date = date;
}
public Calendar() {
	this.user_id = 0;
	this.date = "";
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
}
