package dto;
import java.io.Serializable;
public class IconStatus implements Serializable{
	//フィールド
	private int user_id;
	private int icon_id;
	//コンストラクタ
	public IconStatus(int user_id,int icon_id) {
		super();
		this.user_id = user_id;
		this.icon_id = icon_id;
	}
	public IconStatus() {
		super();
		this.user_id = 0;
		this.icon_id = 0;
	}
	//ゲッターセッター
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}
	
}
