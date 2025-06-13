package dto;

import java.io.Serializable;
import java.sql.Date;

public class Calendar implements Serializable{
private int user_id;
private Date date;
public Calendar(int user_id, Date date) {
	super();
	this.user_id = user_id;
	this.date = date;
}
public Calendar() {
	this.user_id = 0;
	this.date = new Date(0);  // 1970-01-01 00:00:00 を初期値に設定
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
}
