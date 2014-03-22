package models;

import java.util.Date;

import org.ektorp.support.TypeDiscriminator;

import com.fasterxml.jackson.annotation.JsonProperty;

@TypeDiscriminator("doc.type == 'Note'")
public class Note extends DocumentEntity {

	private static final long serialVersionUID = 1L;

	public String text;

	public Date dateCreated = new Date();

	public String assignedToId;

	public String requirementId;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	@JsonProperty("date_created")
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(String assignedToId) {
		this.assignedToId = assignedToId;
	}

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

}