package dto;

import java.io.Serializable;

public class LoginUsers implements Serializable {
	private int id; // ログイン時のID

	public int getId() {
		return id;
	}

	public void setUserId(int id) {
		this.id = id;
	}

	public LoginUsers() {
		this(0);
	}

	public LoginUsers(int id) {
		this.id = id;
	}
}