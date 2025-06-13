package dto;

import java.io.Serializable;

public class LoginUsers implements Serializable {
	private String id; // ログイン時のID

	public String getId() {
		return id;
	}

	public void setUserId(String id) {
		this.id = id;
	}

	public LoginUsers() {
		this(null);
	}

	public LoginUsers(String id) {
		this.id = id;
	}
}