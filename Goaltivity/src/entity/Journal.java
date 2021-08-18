package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Journal {
	private int id;
	private Date date;
	private String title;
	private String content;
	private int user;
	private List<Tag> tags;
	
	public Journal(int id, Date date, String title, String content) {
		this.setUser(-1);
		this.id = id;
		this.setDate(date);
		this.title = title;
		this.content = content;
		this.tags = new ArrayList<Tag>();
	}
	
	public Journal(int id2, Date date2, String title2, String content2, int userById) {
		this.setUser(-1);
		this.id = id2;
		this.setDate(date2);
		this.title = title2;
		this.content = content2;
		this.user = userById;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public List<Tag> getTags() {
		return tags;
	}
	
	public void addTag(Tag tag) {
		if (tag != null) {
			tags.add(tag);
		}
	}
}