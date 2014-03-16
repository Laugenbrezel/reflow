package models;

import org.ektorp.support.CouchDbDocument;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class DocumentEntity extends CouchDbDocument {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String type;

	protected DocumentEntity() {
		this.type = this.getClass().getSimpleName();
	}
}
