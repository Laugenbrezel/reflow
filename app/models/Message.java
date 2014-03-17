package models;

import org.ektorp.support.TypeDiscriminator;

import play.data.validation.Constraints.Required;

@TypeDiscriminator("doc.type == 'Message'")
public class Message extends DocumentEntity {

	private static final long serialVersionUID = 1L;

	private String creatorId;
	@Required
	public String text;

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
