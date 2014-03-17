package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ektorp.docref.DocumentReferences;
import org.ektorp.docref.FetchType;
import org.ektorp.support.TypeDiscriminator;
import org.hibernate.validator.constraints.NotBlank;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;

import controllers.EktorpDb;
import controllers.UserRepository;

@TypeDiscriminator("doc.type == 'Requirement'")
public class Requirement extends DocumentEntity {

	private static final long serialVersionUID = 1L;

	private static final UserRepository USER_REPO = new UserRepository(
			EktorpDb.getDb());

	@Required
	public String title;
	@NotBlank
	@MaxLength(140)
	public String text;

	public String creatorId;

	public List<String> stakeholders = new ArrayList<String>();

	public List<String> likes = new ArrayList<String>();

	@DocumentReferences(backReference = "assignedToId", fetch = FetchType.LAZY)
	public Set<Note> notes = new HashSet<Note>();

	public Requirement() {
	}

	public Requirement(String title, String text, String creatorId) {
		this.title = title;
		this.text = text;
		this.creatorId = creatorId;
		this.stakeholders.add(creatorId);
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

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreator(String creatorId) {
		this.creatorId = creatorId;
	}

	@JsonIgnore
	public User getCreator() {
		// TODO how best to do this?
		return USER_REPO.get(this.creatorId);
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

	public boolean likedBy(String userId) {
		return likes.contains(userId);
	}

}
