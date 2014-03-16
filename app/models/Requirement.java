package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ektorp.docref.DocumentReferences;
import org.ektorp.docref.FetchType;
import org.hibernate.validator.constraints.NotBlank;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

public class Requirement extends DocumentEntity {

	private static final long serialVersionUID = 1L;

	@Required
	public String title;
	@NotBlank
	@MaxLength(140)
	public String text;

	public User creator;

	public List<String> stakeholders = new ArrayList<String>();

	public List<String> likes = new ArrayList<String>();

	@DocumentReferences(backReference = "assignedToId", fetch = FetchType.LAZY)
	public Set<Note> notes = new HashSet<Note>();

	public Requirement() {
	}

	public Requirement(String title, String text, User owner) {
		this.title = title;
		this.text = text;
		this.creator = owner;
		this.stakeholders.add(owner.getId());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<String> getStakeholders() {
		return stakeholders;
	}

	public void setStakeholders(List<String> stakeholders) {
		this.stakeholders = stakeholders;
	}

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public boolean likedBy(User user) {
		return likes.contains(user.getId());
	}

}
