package entity;

import java.util.ArrayList;
import java.util.List;
public class JournalTags {
	private int journalId;
	private int tagId;
	private List<Tag> tags;
	public JournalTags(int journalId, int tagId) {
		this.journalId = journalId;
		this.tagId = tagId;
		this.tags = new ArrayList<Tag>();
	}
	public int getJournalId() {
		return journalId;
	}
	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}