package dto;
import java.io.Serializable;
public class Icon implements Serializable{
	private int icon_id;
	private String icon_name;
	private int price;
	// 
	public Icon(int icon_id,String icon_name,int price) {
		super();
		this.icon_id = icon_id;
		this.icon_name = icon_name;
		this.price = price;
	}
	public int getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
