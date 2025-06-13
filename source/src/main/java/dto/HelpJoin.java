package dto;
import java.io.Serializable;

public class HelpJoin implements Serializable{
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public HelpJoin(String link) {
		super();
		this.link = link;
	}
	public HelpJoin() {
		super();
		this.link = "";
	}
}
