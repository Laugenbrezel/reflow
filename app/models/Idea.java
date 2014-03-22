package models;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.support.TypeDiscriminator;

import play.data.validation.Constraints.Required;

@TypeDiscriminator("doc.type == 'Idea'")
public class Idea extends DocumentEntity {

	private static final long serialVersionUID = 1L;

	@Required
	public String text;

	public String creatorId;

	public IdeaStatus status;

	public List<String> likes = new ArrayList<>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public IdeaStatus getStatus() {
		return status;
	}

	public void setStatus(IdeaStatus status) {
		this.status = status;
	}

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}
}
